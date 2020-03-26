import React, {useState} from "react";
import {BrowserRouter as Router, Route, Switch} from "react-router-dom";
import AppBar from "@material-ui/core/AppBar";
import Toolbar from "@material-ui/core/Toolbar";
import CssBaseline from "@material-ui/core/CssBaseline";
import useMediaQuery from "@material-ui/core/useMediaQuery";
import {createMuiTheme, ThemeProvider} from "@material-ui/core/styles";
import IconButton from "@material-ui/core/IconButton";
import WbSunnyIcon from "@material-ui/icons/WbSunny";
import Brightness3Icon from "@material-ui/icons/Brightness3";
import {SnackbarProvider} from "notistack";
import {makeStyles} from "@material-ui/core/styles";

import BuildEditor from "./BuildEditor";
import Logo from "../logo.png";

const useStyles = makeStyles(theme => ({
  grow: {
    flexGrow: 1,
  },
  header: {
    background: "#b6975a",
    background:
      "linear-gradient(90deg, rgba(83,72,51,1) 6%, rgba(182,151,90,1) 48%, rgba(111,95,64,1) 91%)",
  },
  img: {
    height: 43,
  },
}));

function App() {
  // We keep the theme in app state
  const [theme, setTheme] = useState({
    palette: {
      type: useMediaQuery("(prefers-color-scheme: light)") ? "light" : "dark",
      primary: {main: "#b6975a"},
    },
  });
  // we change the palette type of the theme in state
  const toggleDarkTheme = () => {
    const newPaletteType = theme.palette.type === "light" ? "dark" : "light";
    setTheme({
      palette: {
        type: newPaletteType,
      },
    });
  };

  // we generate a MUI-theme from state's theme object
  const muiTheme = createMuiTheme(theme);

  const classes = useStyles();

  return (
    <ThemeProvider theme={muiTheme}>
      <SnackbarProvider maxSnack={3}>
        <CssBaseline />
        <Router>
          <AppBar position="static" className={classes.header}>
            <Toolbar>
              <img src={Logo} className={classes.img} />
              <div className={classes.grow} />
              <IconButton
                edge="start"
                color="inherit"
                aria-label="Переключить тему"
                onClick={toggleDarkTheme}
              >
                {theme.palette.type === "dark" ? <Brightness3Icon /> : <WbSunnyIcon />}
              </IconButton>
            </Toolbar>
          </AppBar>
          <Switch>
            <Route path="/" exact component={BuildEditor} />
            <Route path="/courses" exact component={() => "1"} />
          </Switch>
        </Router>
      </SnackbarProvider>
    </ThemeProvider>
  );
}

export default App;
