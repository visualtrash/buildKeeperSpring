import React, { Component } from "react";
import { withStyles } from "@material-ui/core/styles";
import TextField from "@material-ui/core/TextField";
import Card from "@material-ui/core/Card";
import CardContent from "@material-ui/core/CardContent";

import { withSnackbar } from "notistack";

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
        width: 43,
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

class RuneSelect extends Component {
    constructor(props) {
        super(props);
        this.inputRefs = {};
        props.selectedItems.forEach((_val, index) => {
            this.inputRefs[`${index}`] = React.createRef();
        });
    }

    handleChange = ({ target: { name: index, value } }) => {
        const { onChange, selectedItems } = this.props;
        let upperCaseValue = value.toUpperCase().replace("-", "");
        upperCaseValue =
            upperCaseValue.length > 1 ? upperCaseValue.charAt(1) : upperCaseValue;

        if (["0", "1", "2", "3", "4", "5"].includes(upperCaseValue) || upperCaseValue === '') {
            onChange(index, upperCaseValue === '0' ? '' : upperCaseValue);

            if (upperCaseValue === '' && Number(index) > 0) {
                this.inputRefs[`${Number(index) - 1}`].current.focus();
            } else if (Number(index) < selectedItems.length - 1) {
                this.inputRefs[`${Number(index) + 1}`].current.focus();
            }
        }
    };

    render() {
        const { classes, selectedItems } = this.props;

        return (
            <Card className={classes.root} variant="outlined">
                <CardContent className={classes.content}>
                    <div className={classes.gridList}>
                        {selectedItems.map((_value, index) => (
                            <TextField
                                inputRef={this.inputRefs[`${index}`]}
                                error={!["1", "2", "3", "4", "5", ""].includes(selectedItems[index])}
                                className={classes.item}
                                key={`${index}`}
                                name={`${index}`}
                                label={`${index + 1}`}
                                variant="outlined"
                                color="secondary"
                                value={selectedItems[index] || "-"}
                                onChange={this.handleChange}
                            />
                        ))}
                    </div>
                </CardContent>
            </Card>
        );
    }
}

export default withStyles(styles)(withSnackbar(RuneSelect));
