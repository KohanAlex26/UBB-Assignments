import { useState } from "react";
import "../CSS/Nav.css";
import profile from "../Images/popescu.jfif";
import ubb_stema from "../Images/sigla_UBB_alb.png";
import { Link } from "react-router-dom";

export default function NavBar() {
  const [navclass, setnavclass] = useState("");
  const [burgerclass, setburgerclass] = useState("");
  const [navlinkclass, setnavlinkclass] = useState("");

  const handleclick = () => {
    setnavclass(navclass === "" ? "nav-open" : "");
    setburgerclass(burgerclass === "" ? "burger-open" : "");
    setnavlinkclass(navlinkclass === "" ? "nav-link-open" : "");
  };

  return (
    <div className="Wraper">
      <div className="Nav">
        <div className="Profile">
          <img className="profile_pic" src={profile}></img>
          <div className="Profile_info">
                  <h3>Romulus Bizau</h3>
                  <h3>Matematica-informatica Engleza</h3>
                  <h3>FMI</h3>
          </div>
        </div>
        <div>
          <img className="UBB" src={ubb_stema}></img>
        </div>
        <div className={`burger ${burgerclass}`} onClick={handleclick}>
          <i className="fas fa-bars fa-2x"></i>
          <i className="fas fa-times fa-2x"></i>
        </div>

        <nav className={`navbar ${navclass}`}>
          <ul className="nav-links">
            <li className={`nav-link ${navlinkclass}`}>
              <a href="/">CStudent</a>
            </li>
            <li className={`nav-link ${navlinkclass}`}>
              <Link to="/OrarStudent">Or
                <Link to="/OrarProfesor">ar</Link>
              </Link>
            </li>
            <li className={`nav-link ${navlinkclass}`}>
            <Link to="/Harta">Harta</Link>
            </li>
            <li className={`nav-link ${navlinkclass}`}>
              <a href="/Examen">Exam
                <a className={`nav-links2 ${navlinkclass}`} href="/ExamenProfesor">ene</a>
              </a>
            </li>
            <li className={`nav-link ${navlinkclass}`}>
              <a href="/Camin">Camin</a>
            </li>
          </ul>
        </nav>
      </div>
    </div>
  );
}
