import React from "react";
import CssBaseline from "@material-ui/core/CssBaseline";
import { SnackbarProvider } from "notistack";
import { createMuiTheme, ThemeProvider } from "@material-ui/core/styles";
import { LocationProvider } from "@reach/router";

const theme = createMuiTheme({
  palette: {
    type: "dark",
  },
});

export default function Root({ children }) {

  return (
    <LocationProvider>
      <CssBaseline />
      <ThemeProvider theme={theme}>
        <SnackbarProvider maxSnack={3}>
          {children}
        </SnackbarProvider>
      </ThemeProvider>
    </LocationProvider>
  );
}
