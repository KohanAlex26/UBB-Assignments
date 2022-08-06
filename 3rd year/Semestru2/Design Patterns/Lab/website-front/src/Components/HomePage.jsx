import NavBar from "./Navbar";
import Content from "./Content";
import A from "../CSS/App.module.css";
import { Navigate } from "react-router-dom";

export default function HomePage(props) {
  return props.isLoggedIn === false ? (
    <Navigate push to="/login" />
  ) : (
    <div className={A.App}>
        <NavBar />
      <Content />
    </div>
  );
}
