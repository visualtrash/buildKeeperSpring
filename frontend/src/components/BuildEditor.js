import React, {Component} from "react";
import TextField from "@material-ui/core/TextField";
import HeroSelect from "./HeroSelect";
import PositionSelect from "./PositionSelect";
import ItemSelect from "./ItemSelect";
import {withStyles} from "@material-ui/core/styles";
import TypoGraphy from "@material-ui/core/Typography";
import CloudUploadIcon from "@material-ui/icons/CloudUpload";
import Button from "@material-ui/core/Button";
import {withSnackbar} from "notistack";
import Axios from "axios";
import AbilitySelect from "./AbilitySelect";

const styles = theme => ({
  root: {
    display: "flex",
    flexWrap: "wrap",
    justifyContent: "space-around",
    overflow: "hidden",
    flexDirection: "column",
  },
  label: {
    paddingLeft: 16,
  },
  button: {
    margin: theme.spacing(1),
  },
});

class BuildEditor extends Component {
  state = {
    name: "",
    position: undefined,
    hero: undefined,
    items: [],
    abilities: ["", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""],
  };

  createBuild = () => {
    const {name, position, hero, items, abilities} = this.state;
    const {enqueueSnackbar} = this.props;
    if (!name) {
      enqueueSnackbar("Имя билда обязательно для заполнения", {
        variant: "error",
      });
      return;
    }
    if (!position) {
      enqueueSnackbar("Позиция героя обязательна для заполнения", {
        variant: "error",
      });
      return;
    }
    if (!hero) {
      enqueueSnackbar("Нужно выбрать героя", {
        variant: "error",
      });
      return;
    }
    if (!items.length) {
      enqueueSnackbar("Нужно выбрать несколько вещей для героя", {
        variant: "error",
      });
      return;
    }

    Axios.post("http://localhost:8080/api/builds/create", {
      name,
      heroPosition: position.id,
      hero,
      items,
      abilities: abilities.join("-"),
    })
      .then(res => {
        enqueueSnackbar("Новый билд добавлен!", {
          variant: "success",
        });
      })
      .catch(res => {
        enqueueSnackbar("Что то пошло не так :(", {
          variant: "error",
        });
      });
  };

  handleNameChange = ev => {
    this.setState({name: ev.target.value});
  };

  handleHeroSelect = hero => {
    this.setState({hero});
  };

  handlePositionSelect = position => {
    this.setState({position});
  };

  handleHeroDeselect = () => {
    this.setState({hero: null});
  };

  handleItemSelect = item => {
    const {items} = this.state;
    const newItems = [...items, item];
    this.setState({items: newItems});
  };

  handleItemDeselect = item => {
    const {items} = this.state;
    const newItems = [...items.filter(i => i !== item)];
    this.setState({items: newItems});
  };

  handleAbilitiesChange = (index, ability) => {
    const {abilities} = this.state;
    abilities[Number(index)] = ability;

    this.setState({abilities: [...abilities]});
  };

  render() {
    const {name, hero, items, position, abilities} = this.state;
    const {classes} = this.props;

    return (
      <div className={classes.root}>
        <TextField
          id="build-name"
          label="1. Название билда"
          variant="filled"
          value={name}
          onChange={this.handleNameChange}
        />
        <TypoGraphy className={classes.label} variant="subtitle1">
          2. Выберете героя
        </TypoGraphy>
        <HeroSelect
          selected={hero}
          onSelect={this.handleHeroSelect}
          onDeselect={this.handleHeroDeselect}
        />
        <TypoGraphy className={classes.label} variant="subtitle1">
          3. Выберете позицию
        </TypoGraphy>
        <PositionSelect selected={position} onSelect={this.handlePositionSelect} />
        <TypoGraphy className={classes.label} variant="subtitle1">
          4. Выберете вещи
        </TypoGraphy>
        <ItemSelect
          selectedItems={items}
          onDeselect={this.handleItemDeselect}
          onSelect={this.handleItemSelect}
        />
        <TypoGraphy className={classes.label} variant="subtitle1">
          4. Выберете способности
        </TypoGraphy>
        <AbilitySelect selectedItems={abilities} onChange={this.handleAbilitiesChange} />
        <Button
          variant="contained"
          className={classes.button}
          startIcon={<CloudUploadIcon />}
          onClick={this.createBuild}
        >
          Создать билд
        </Button>
      </div>
    );
  }
}

export default withStyles(styles)(withSnackbar(BuildEditor));
