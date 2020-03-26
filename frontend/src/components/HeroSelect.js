import React, {Component} from "react";
import {withStyles} from "@material-ui/core/styles";
import SearchIcon from "@material-ui/icons/Search";
import TextField from "@material-ui/core/TextField";
import InputAdornment from "@material-ui/core/InputAdornment";
import Card from "@material-ui/core/Card";
import CardActionArea from "@material-ui/core/CardActionArea";
import CardContent from "@material-ui/core/CardContent";
import AddPng from "../add.png";
import Axios from "axios";
import {withSnackbar} from "notistack";

const styles = theme => ({
  root: {
    display: "flex",
    flexWrap: "wrap",
    justifyContent: "space-around",
    overflow: "hidden",
    backgroundColor: theme.palette.background.paper,
    flexDirection: "column",
  },
  content: {
    padding: 4,
  },
  gridList: {
    width: "100%",
    height: 72,
    display: "flex",
    flexDirection: "row",
    flexWrap: "wrap",
    fontSize: 11,
  },
  selectedList: {
    width: "100%",
    minHeight: 80,
    display: "flex",
    flexDirection: "row",
    flexWrap: "wrap",
    fontSize: 11,
    justifyContent: "flex-start",
    paddingLeft: 4,
  },
  item: {
    height: 90,
    width: 90,
    display: "flex",
    margin: 4,
    transition: "all .2s ease-in-out",
    "&:hover": {
      transform: "scale(1.1)",
      cursor: "pointer",
    },
  },
  selected: {
    background: "#8b6d30",
    borderRadius: 16,
  },
  img: {
    height: "inherit",
    width: "inherit",
    position: "absolute",
  },
  search: {
    padding: "2px 0",
    marginLeft: 4,
    width: "100%",
  },
  itemLabel: {
    zIndex: 9,
    background: "#00000094",
    width: "100%",
    height: "50%",
    marginTop: "50%",
    padding: 2,
  },
});

class HeroSelect extends Component {
  state = {
    items: [],
    filter: "",
  };

  setFilter = ev => {
    this.setState({filter: ev.target.value});
  };

  componentDidMount() {
    const {enqueueSnackbar} = this.props;

    Axios.get("http://localhost:8080/api/heroes/getAll")
      .then(res => {
        this.setState({items: res.data});
      })
      .catch(res => {
        enqueueSnackbar("Что то пошло не так :(", {
          variant: "error",
        });
      });
  }

  render() {
    const {classes, selected, onDeselect, onSelect} = this.props;
    const {items, filter} = this.state;

    const filtered = filter
      ? items.filter(i => i.name.toLowerCase().includes(filter.toLowerCase()))
      : items;

    return (
      <Card className={classes.root} variant="outlined">
        <CardActionArea className={classes.selectedList}>
          {selected ? (
            <div
              style={{
                backgroundImage: "url(" + selected.img + ")",
                backgroundRepeat: "round",
              }}
              className={`${classes.item} ${classes.selected}`}
              onClick={() => onDeselect(selected)}
            >
              <div className={classes.itemLabel}>
                <span>{selected.name}</span>
              </div>
            </div>
          ) : (
            <div
              style={{backgroundImage: "url(" + AddPng + ")", backgroundRepeat: "round"}}
              className={classes.item}
            >
              <div className={classes.itemLabel}>
                <span>Не выбран</span>
              </div>
            </div>
          )}
        </CardActionArea>
        <CardContent className={classes.content}>
          <TextField
            className={classes.search}
            value={filter}
            onChange={this.setFilter}
            InputProps={{
              startAdornment: (
                <InputAdornment position="end">
                  <SearchIcon />
                </InputAdornment>
              ),
            }}
          />
          <div className={classes.gridList}>
            {filtered.map(item => (
              <div
                style={{
                  backgroundImage: "url(" + item.img + ")",
                  backgroundRepeat: "round",
                }}
                className={classes.item}
                key={item.id}
                onClick={() => onSelect({...item})}
              >
                <div className={classes.itemLabel}>
                  <span>{item.name}</span>
                  <div>{item.price}</div>
                </div>
              </div>
            ))}
          </div>
        </CardContent>
      </Card>
    );
  }
}

export default withStyles(styles)(withSnackbar(HeroSelect));
