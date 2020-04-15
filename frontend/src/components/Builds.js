import React, { Component } from "react";
import { withStyles } from "@material-ui/core/styles";
import GridList from "@material-ui/core/GridList";
import GridListTile from "@material-ui/core/GridListTile";
import GridListTileBar from "@material-ui/core/GridListTileBar";
import ListSubheader from "@material-ui/core/ListSubheader";
import IconButton from "@material-ui/core/IconButton";
import VisibilityIcon from '@material-ui/icons/Visibility';
import { withSnackbar } from "notistack";
import Axios from "axios";
import Typography from "@material-ui/core/Typography";
import {
  navigate
} from "@reach/router";

const styles = (theme) => ({
  root: {
    display: "flex",
    flexWrap: "wrap",
    justifyContent: "space-around",
    overflow: "hidden",
    backgroundColor: theme.palette.background.paper,
  },
  gridList: {
    width: 500,
    height: 450,
  },
  icon: {
    color: "rgba(255, 255, 255, 0.54)",
  },
});

class Builds extends Component {
  state = { builds: [] };

  componentDidMount() {
      const { enqueueSnackbar } = this.props;

      Axios.get("http://localhost:8080/api/builds/getAll")
        .then((res) => {
          this.setState({ builds: res.data });
        })
        .catch(() => {
          enqueueSnackbar("Что то пошло не так :(", {
            variant: "error",
          });
        });
  }

  render() {
    const { builds } = this.state;
    const { classes } = this.props;

    return (
      <>
        {builds.length > 0 ? (
          <GridList cellHeight={180} className={classes.gridList}>
            <GridListTile key="Subheader" cols={6} style={{ height: "auto" }}>
              <ListSubheader component="div">December</ListSubheader>
            </GridListTile>
            {builds.map((build) => (
              <GridListTile key={build.id}>
                <img src={build.hero.img} alt={build.name} />
                <GridListTileBar
                  title={build.name}
                  subtitle={<span>for: {build.hero.name}</span>}
                  actionIcon={
                    <IconButton
                        aria-label={`Open ${build.name} #${build.id}`}
                        className={classes.icon}
                        onClick={() => navigate(`builds/${build.id}`)
                    }
                    >
                      <VisibilityIcon />
                    </IconButton>
                  }
                />
              </GridListTile>
            ))}
          </GridList>
        ) : (
          <Typography className={classes.label} variant="subtitle1">
            Билдов еще нет, создай их на вкладке НОВЫЙ БИЛД
          </Typography>
        )}
      </>
    );
  }
}

export default withStyles(styles)(withSnackbar(Builds));
