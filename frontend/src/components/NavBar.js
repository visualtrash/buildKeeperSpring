import React, { useMemo } from "react";
import { Link, useLocation } from "@reach/router";
import cx from "classnames";
import { makeStyles } from "@material-ui/core/styles";
import useMediaQuery from "@material-ui/core/useMediaQuery";
import List from "@material-ui/core/List";
import ListItemText from "@material-ui/core/ListItemText";
import Menu from "@material-ui/core/Menu";
import MenuItem from "@material-ui/core/MenuItem";
import IconButton from "@material-ui/core/IconButton";
import MenuIcon from "@material-ui/icons/Menu";
import TypoGraphy from "@material-ui/core/Typography";

const navConfig = [
  { text: "Новый билд", to: "/" },
  { text: "Твои билды", to: "/builds" },
];

const useStyles = makeStyles(theme => ({
  navList: {
    padding: 0,
    display: "inline-flex",
  },
  link: {
    color: "inherit",
    textDecoration: "none",
    "&:hover": {
      color: theme.palette.text.secondary,
    },
    "&.active": {
      color: theme.palette.text.secondary,
    },
  },
}));

const NavItem = ({ text, to }) => {
  const classes = useStyles();
  const location = useLocation();

  // TODO fix react-hooks/exhaustive-deps for location.pathname
  const isActive = useMemo(
    () => ({ isCurrent }) => ({
      className: cx(classes.link, { active: isCurrent }),
    }),
    // eslint-disable-next-line react-hooks/exhaustive-deps
    [location.pathname, classes.link]
  );

  return (
    <Link to={to} getProps={isActive} color="inherit">
      <TypoGraphy color="inherit" variant="button">
        {text}
      </TypoGraphy>
    </Link>
  );
};

const BurgerMenu = () => {
  const [anchorEl, setAnchorEl] = React.useState(null);

  const handleClick = event => {
    setAnchorEl(event.currentTarget);
  };

  const handleClose = () => {
    setAnchorEl(null);
  };

  return (
    <>
      <IconButton
        color="inherit"
        aria-controls="nav-menu"
        aria-haspopup="true"
        aria-label="open menu"
        onClick={handleClick}
      >
        <MenuIcon />
      </IconButton>
      <Menu
        component="nav"
        getContentAnchorEl={null}
        anchorOrigin={{
          vertical: "bottom",
          horizontal: "right",
        }}
        transformOrigin={{
          vertical: "top",
          horizontal: "center",
        }}
        id="nav-menu"
        anchorEl={anchorEl}
        open={Boolean(anchorEl)}
        onClose={handleClose}
      >
        {navConfig.map(link => (
          <MenuItem key={`${link.text}_${link.to}`} onClick={handleClose}>
            <NavItem text={link.text} to={link.to} />
          </MenuItem>
        ))}
      </Menu>
    </>
  );
};

const NavItemsList = () => {
  const classes = useStyles();

  return (
    <nav>
      <List className={classes.navList}>
        {navConfig.map(link => (
          <ListItemText key={`${link.text}_${link.to}`} inset>
            <NavItem text={link.text} to={link.to} />
          </ListItemText>
        ))}
      </List>
    </nav>
  );
};

export default function NavBar() {
  const isMobileDevice = useMediaQuery("only screen and (max-width: 768px)");

  return isMobileDevice ? <BurgerMenu /> : <NavItemsList />;
}
