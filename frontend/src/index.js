import React from "react";
import ReactDOM from "react-dom";
import App from "./components/App";
import Root from "./components/Root";

ReactDOM.render(
  <React.StrictMode>
    <Root>
      <App />
    </Root>
  </React.StrictMode>,
  document.getElementById("root")
);
