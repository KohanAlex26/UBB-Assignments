import "../CSS/Content_Containers.css";
import "../CSS/Buton.css";
import { useState } from "react";

export default function Content() {
  const url_start =
      "https://www.cs.ubbcluj.ro/";
  const [IframeURL, setIframeURL] = useState(
      url_start
  );

  const onClickChangeIframeURL = (e) => {
    console.log(IframeURL);
    setIframeURL("https://www.cs.ubbcluj.ro/anunturi/anunturi-studenti/");
  };


  return (
    <div className="content">
      <div className="anunturi">
        <a
          className="buton_MainPage"
          href="https://www.cs.ubbcluj.ro/anunturi/anunturi-studenti/"
          alt="Anunturi"
        >
          <span className="text">Anunturi</span>
        </a>
      </div>
      <div className="linkuri_utile">Link-uri utile</div>
      <div className="iframe_links">
        <div className="anunturi_content">
          <div className="Camera"></div>

          <iframe
            src={IframeURL}
            title="Anunturi Studenti"
          ></iframe>

          {/*WORKS ONLY ONCE*/}
         <button className="IpadButton"
             // onClick={() => onClickChangeIframeURL("https://www.cs.ubbcluj.ro/anunturi/anunturi-studenti/")}
             onClick={(e) => onClickChangeIframeURL(e)}
             typeof="button"
          >
              <a className="square"/>

          </button>

        </div>
        <div className="links_container">
          <div className="FMI">
            <a
              className="buton_MainPage"
              href="https://www.cs.ubbcluj.ro/"
              alt="Anunturi"
            >
              <span className="text">FMI</span>
            </a>
          </div>
          <div className="Moodle">
            <a
              className="buton_MainPage"
              href="https://moodle.cs.ubbcluj.ro/"
              alt="Anunturi"
            >
              <span className="text">Moodle</span>
            </a>
          </div>
          <div className="Academic_Info">
            <a
              className="buton_MainPage"
              href="https://academicinfo.ubbcluj.ro/Info/"
              alt="Anunturi"
            >
              <span className="text">Academic Info</span>
            </a>
          </div>
          <div className="OutLook">
            <a
              className="buton_MainPage"
              href="https://outlook.office365.com/mail/inbox"
              alt="Anunturi"
            >
              <span className="text">OutLook</span>
            </a>
          </div>
        </div>
      </div>
    </div>
  );
}
