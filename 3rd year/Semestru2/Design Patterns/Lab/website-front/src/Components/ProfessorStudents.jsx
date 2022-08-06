import NavBar from "../Components/Navbar.jsx";
import A from "../CSS/App.module.css"
import "../CSS/ExamProfessor.css"
import {useEffect, useState} from "react";
import styled, {css} from "styled-components"


const TableRow = styled.tr`
    min-height: 100px;
    width: 100%;

    td{
    ${props => css`background-color: #93c47d;`}
    }
`


export default function ExamenProfessor() {


    const [a, setA] = useState([])

    useEffect(async () => {
        await getData()
    }, [])


    // const getData = async () => {
    //     fetch('http://localhost:8080/api/exams', {
    //         method: 'GET',
    //     }).then(r => r.json()).then(data => {
    //         setA(data)
    //     })
    // }

    const getData = async () => {
        let url = ""
        // url = 'http://localhost:8080/api/courses/' + document.getElementById("Id").value
        url = 'http://localhost:8080/api/courses/eb280205-c3d0-4136-8601-2d8f54dd32b6'
        fetch(url, {
            method: 'GET',
        }).then(r => r.json()).then(data => setA(data))
        let url2 = ""
        url2 = 'http://localhost:8080/api/course/5a862c8d-adb1-4e18-a26a-51784b52febe'
        fetch(url2, {
            method: 'GET',
        }).then(r => r.json()).then(data => setA(data))

    }
    const getStudents = async () => {
        let url = ""
        url = 'http://localhost:8080/api/course/' + document.getElementById("Id").value
        fetch(url, {
            method: 'GET',
        }).then(r => r.json()).then(data => setA(data))
    }


    return (
        <div className={A.App}>
            <NavBar/>


            <div id="table-container">
                <table className="styled-table-exam-courses">
                    <thead>
                    <TableRow>
                        <th>ID</th>
                        <th>Nume curs</th>
                    </TableRow>
                    </thead>
                    <tbody style={{width: "100%"}}>
                    {
                        a.map(curs => {
                            return <>
                                <TableRow>
                                    <td>{curs.id}</td>
                                    <td>{curs.courseName}</td>
                                </TableRow>
                            </>
                        })
                    }
                    </tbody>
                </table>
            </div>

            <div id="table-container">
                <table className="styled-table-exam-students">
                    <thead>
                    <TableRow>
                        <th>Grupa</th>
                        <th>Prenume</th>
                        <th>Nume</th>
                        <th>Numar matricol</th>
                        <th>email</th>

                    </TableRow>
                    </thead>
                    <tbody style={{width: "100%"}}>
                    {
                        a.map(student => {
                            return <>
                                <TableRow>
                                    <td>{student.group}</td>
                                    <td>{student.firstName}</td>
                                    <td>{student.lastName}</td>
                                    <td>{student.registrationNumber}</td>
                                    <td>{student.email}</td>
                                </TableRow>
                            </>
                        })
                    }
                    </tbody>
                </table>
            </div>

            <div className="form-group">
                <label htmlFor="Id">Id &nbsp;</label>
                <input type="text" id="Id" name="Id"/>
            </div>
            <button onClick={getStudents} className="buttonsLoginStyle2">Adauga</button>


        </div>

    );

}
