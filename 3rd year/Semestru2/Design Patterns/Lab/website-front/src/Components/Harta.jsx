import "../CSS/Harta.css";
import { useState } from "react";
import React from "react";
import NavBar from "../Components/Navbar.jsx";
import A from "../CSS/App.module.css"

export default function Harta() {
  const url_start =
    "https://www.google.com/maps/embed/v1/place?key=AIzaSyDn5TU07R040YBRD-9ePpM8Noh-Z1NNVyw&q=";
  const [IframeURL, setIframeURL] = useState(
    url_start + "46.767636,23.5926933"
  );

  const onClickChangeIframeURL = (e) => {
    setIframeURL(url_start + e.target.value);
    console.log(e.target.value);
  };

  return (
    <div className={A.App}>
        <NavBar/>
      <body>
        <div className="navbar">
          <a href="#home">Home</a>
          <a href="#orar">Time Table</a>
          <a href="#home">Camera mea</a>
          <a href="#orar">Examene</a>
        </div>

        <div className="grid-container">
          <div
            style={{
              borderRadius: "4vh",
              width: "160vh",
              height: "93vh",
              overflow: "hidden",
            }}
          >
            <div className="item1">
              <div className="map_tablet">
                <div className="map_Camera"></div>
                <iframe
                    width="1400"
                    height="750"
                    frameBorder="0"
                    style={{border: "0"}}
                    allowFullScreen
                    src={IframeURL}
                />
                <button
                        onClick={(e) => onClickChangeIframeURL(e)}
                        value={["46.767636", "23.5926933"]}
                        className="map_IpadButton"
                        typeof="button"
                >
                  <button className="map_square"

                  />

                </button>

              </div>
            </div>
          </div>
          <div className="item2">
            <div className="btn-group-vertical">
              <button
                onClick={(e) => onClickChangeIframeURL(e)}
                value={["46.7628934", "23.5744188"]}
                className="button_down"
                typeof="button"
              >
                Camin A1
              </button>
              <button
                onClick={(e) => onClickChangeIframeURL(e)}
                value={["46.7628934", "23.5744188"]}
                className="button_up"
                typeof="button"
              >
                Str. B.P.Haşdeu nr. 90-92
              </button>
            </div>
            <div className="btn-group-vertical">
              <button
                onClick={(e) => onClickChangeIframeURL(e)}
                value={["46.7628934", "23.5744188"]}
                className="button_down"
                typeof="button"
              >
                Camin A2
              </button>
              <button
                onClick={(e) => onClickChangeIframeURL(e)}
                value={["46.7628934", "23.5744188"]}
                className="button_up"
                typeof="button"
              >
                Str. B.P.Haşdeu nr. 90-92
              </button>
            </div>
            <div className="btn-group-vertical">
              <button
                onClick={(e) => onClickChangeIframeURL(e)}
                value={["46.7749177", "23.6192103"]}
                className="button_down"
                typeof="button"
              >
                Economica 1
              </button>
              <button
                onClick={(e) => onClickChangeIframeURL(e)}
                value={["46.7749177", "23.6192103"]}
                className="button_up"
                typeof="button"
              >
                Str. Teodor Mihali nr.59
              </button>
            </div>
            <div className="btn-group-vertical">
              <button
                onClick={(e) => onClickChangeIframeURL(e)}
                value={["46.7749444", "23.6209248"]}
                className="button_down"
                typeof="button"
              >
                Economica 2
              </button>
              <button
                onClick={(e) => onClickChangeIframeURL(e)}
                value={["46.7746474", "23.6199893"]}
                className="button_up"
                typeof="button"
              >
                Str. Teodor Mihali nr.58
              </button>
            </div>
            <div className="btn-group-vertical">
              <button
                onClick={(e) => onClickChangeIframeURL(e)}
                value={["46.761249", "23.578933"]}
                className="button_down"
                typeof="button"
              >
                Hasdeu 1
              </button>
              <button
                onClick={(e) => onClickChangeIframeURL(e)}
                value={["46.761249", "23.578933"]}
                className="button_up"
                typeof="button"
              >
                Str. : B.P.Haşdeu nr.45
              </button>
            </div>
            <div className="btn-group-vertical">
              <button
                onClick={(e) => onClickChangeIframeURL(e)}
                value={["46.7621375", "23.5802643"]}
                className="button_down"
                typeof="button"
              >
                Hasdeu 2
              </button>
              <button
                onClick={(e) => onClickChangeIframeURL(e)}
                value={["46.761249", "23.578933"]}
                className="button_up"
                typeof="button"
              >
                Str. : B.P.Haşdeu nr.45
              </button>
            </div>
            <div className="btn-group-vertical">
              <button
                onClick={(e) => onClickChangeIframeURL(e)}
                value={["46.761249", "23.578933"]}
                className="button_down"
                typeof="button"
              >
                Hasdeu 3
              </button>
              <button
                onClick={(e) => onClickChangeIframeURL(e)}
                value={["46.761249", "23.578933"]}
                className="button_up"
                typeof="button"
              >
                Str. : B.P.Haşdeu nr.45
              </button>
            </div>
            <div className="btn-group-vertical">
              <button
                onClick={(e) => onClickChangeIframeURL(e)}
                value={["46.761249", "23.578933"]}
                className="button_down"
                typeof="button"
              >
                Hasdeu 4
              </button>
              <button
                onClick={(e) => onClickChangeIframeURL(e)}
                value={["46.761249", "23.578933"]}
                className="button_up"
                typeof="button"
              >
                Str. : B.P.Haşdeu nr.45
              </button>
            </div>
            <div className="btn-group-vertical">
              <button
                onClick={(e) => onClickChangeIframeURL(e)}
                value={["46.761249", "23.578933"]}
                className="button_down"
                typeof="button"
              >
                Hasdeu 5
              </button>
              <button
                onClick={(e) => onClickChangeIframeURL(e)}
                value={["46.761249", "23.578933"]}
                className="button_up"
                typeof="button"
              >
                Str. : B.P.Haşdeu nr.45
              </button>
            </div>
            <div className="btn-group-vertical">
              <button
                onClick={(e) => onClickChangeIframeURL(e)}
                value={["46.761249", "23.578933"]}
                className="button_down"
                typeof="button"
              >
                Hasdeu 6
              </button>
              <button
                onClick={(e) => onClickChangeIframeURL(e)}
                value={["46.761249", "23.578933"]}
                className="button_up"
                typeof="button"
              >
                Str. : B.P.Haşdeu nr.45
              </button>
            </div>
            <div className="btn-group-vertical">
              <button
                onClick={(e) => onClickChangeIframeURL(e)}
                value={["46.761249", "23.578933"]}
                className="button_down"
                typeof="button"
              >
                Hasdeu 7
              </button>
              <button
                onClick={(e) => onClickChangeIframeURL(e)}
                value={["46.761249", "23.578933"]}
                className="button_up"
                typeof="button"
              >
                Str. : B.P.Haşdeu nr.45
              </button>
            </div>
          </div>
        </div>
      </body>
    </div>
  );
}