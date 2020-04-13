import React from "react";
import AppBar from "@material-ui/core/AppBar";
import Toolbar from "@material-ui/core/Toolbar";
import Container from "@material-ui/core/Container";
import { Router } from "@reach/router";
import { makeStyles } from "@material-ui/core";

import Root from "./Root";
import NavBar from "./NavBar";
import Builds from "./Builds";
import About from "./About";
import BuildEditor from "./BuildEditor";
import Logo from "../logo.png";

const useStyles = makeStyles(theme => ({
  header: {
    background:
      "#b6975a linear-gradient(90deg, rgba(83,72,51,1) 6%, rgba(182,151,90,1) 48%, rgba(111,95,64,1) 91%)",
  },
  img: {
    height: 32,
  },
  container: {
    padding: theme.spacing(2)
  }
}));

function App() {
  const classes = useStyles();

  return (
    <Root>
      <AppBar color="primary" position="static" className={classes.header}>
        <Toolbar variant="dense">
          <img src={Logo} className={classes.img} />
          <NavBar />
        </Toolbar>
      </AppBar>
      <Container maxWidth="md" component="main" className={classes.container}>
        <Router>
          <BuildEditor path="/" />
          <Builds exact path="/builds" />
          <BuildEditor path="/builds/:id" />
          <About path="/about" />
        </Router>
      </Container>
    </Root>
  );
}

export default App;
