import React, {Component} from "react";
import {withStyles} from "@material-ui/core/styles";
import Card from "@material-ui/core/Card";
import CardActionArea from "@material-ui/core/CardActionArea";

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
  selected: {
    background: "#8b6d30",
    borderRadius: 16,
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
  img: {
    height: "inherit",
    width: "inherit",
    position: "absolute",
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

const positions = [
  {id: "TOP", img: "https://www.mobafire.com/images/lanes/white-top.png"},
  {id: "JUNGLE", img: "https://www.mobafire.com/images/lanes/white-jungle.png"},
  {id: "MIDDLE", img: "https://www.mobafire.com/images/lanes/white-middle.png"},
  {id: "SUPPORT", img: "https://www.mobafire.com/images/lanes/white-support.png"},
  {id: "BOTTOM", img: "https://www.mobafire.com/images/lanes/white-bottom.png"},
];

class PositionSelect extends Component {
  render() {
    const {classes, selected, onSelect} = this.props;

    return (
      <Card className={classes.root} variant="outlined">
        <CardActionArea className={classes.selectedList}>
          {positions.map(pos => (
            <div
              style={{backgroundImage: "url(" + pos.img + ")", backgroundRepeat: "round"}}
              className={`${classes.item} ${selected &&
                selected.id === pos.id &&
                classes.selected}`}
              onClick={() => onSelect(pos)}
            >
              <div className={classes.itemLabel}>
                <span>{pos.id}</span>
              </div>
            </div>
          ))}
        </CardActionArea>
      </Card>
    );
  }
}

export default withStyles(styles)(PositionSelect);
