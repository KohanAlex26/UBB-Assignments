import React, { useState } from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import HomePage from "./Components/HomePage";
import Login from "./Components/Login";
import Harta from "./Components/Harta";
import OrarStudent from "./Components/OrarStudent";
import Registration from "./Components/Registration";
import Camin from "./Components/Camin";
import ExamenProfessor from "./Components/ExamenProfessor";
import ExamenStudent from "./Components/ExamenStudent";
import OrarProfessor from "./Components/OrarProfessor";
import ProfessorStudents from "./Components/ProfessorStudents"

export default function App() {
    const [isLoggedIn, setIsLoggedIn] = useState();

    return (
        <BrowserRouter >
            <Routes >
            <Route exact path = "/" element = { < HomePage isLoggedIn = { isLoggedIn }/>} />
            <Route path = "/login"
                element = { < Login setIsLoggedIn = { setIsLoggedIn }
            />} />
            <Route path = "/Harta" element = { < Harta />}/>
            <Route path = "/OrarStudent" element = { < OrarStudent /> } />
            <Route path = "/OrarProfesor" element = { < OrarProfessor /> } />
            <Route path = "/Examen" element = { < ExamenStudent /> } />
            <Route path = "/ExamenProfesor" element = { < ExamenProfessor /> } />
            <Route path = "/Signup" element = { < Registration /> } />
            <Route path = "/Camin" element = { < Camin /> } />
            <Route path = "/ListaStudenti" element = { < ProfessorStudents/> } />
            </Routes>
            </BrowserRouter>
            );
    }