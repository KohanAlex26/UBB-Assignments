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


    const getData = async () => {
        fetch('http://localhost:8080/api/exams/professor/499ed492-ed77-459f-bf1a-402baaee1822', {
            method: 'GET',
        }).then(r => r.json()).then(data => {
            setA(data)
        })
    }


    const addExam = async () => {
        fetch('http://localhost:8080/api/addExam', {
            method: 'POST',
            headers: {
                "content-type": "application/json",
            },
            body: JSON.stringify({
                id: '1159d0ac-ef27-411c-9eb3-7e01d7d759ed',
                date: document.getElementById("Data").value,
                courseCode: document.getElementById("CodCurs").value,
                examType: document.getElementById("TipExamen").value == 'colocviu' ? 'Colloquium' : 'CollocutionalExamination',
                formula: document.getElementById("Formula").value,
            })
        }).then(r => r.json()).then(data => console.log(data))
        console.log("S-a adaugat examenul.");
        document.location.reload(true)
    }

    const updateExam = async () => {
        fetch('http://localhost:8080/api/updateExam', {
            method: 'POST',
            headers: {
                "content-type": "application/json",
            },
            body: JSON.stringify({
                id: document.getElementById("Id").value,
                date: document.getElementById("Data").value,
                courseCode: document.getElementById("CodCurs").value,
                examType: document.getElementById("TipExamen").value == 'colocviu' ? 'Colloquium' : 'CollocutionalExamination',
                formula: document.getElementById("Formula").value,
            })
        }).then(r => r.json()).then(data => console.log(data))
        console.log("S-au modificat datele.");
        document.location.reload(true)
    }

    const removeExam = async () => {
        let url='http://localhost:8080/api/deleteExam/'
        url=url+document.getElementById("Id").value
        fetch(url, {
            method: 'DELETE',
        }).then(r => r.json()).then(data => console.log(data))
        console.log("S-a efectuat stergerea.");
        document.location.reload(true)
    }


    return (
        <div className={A.App}>
            <NavBar/>


            <div id="table-container">
                <table className="styled-table-exam">
                    <thead>
                    <TableRow>
                        <th>Data Examen</th>
                        <th>Cod Curs</th>
                        <th>Tip Examen</th>
                        <th>Formatie</th>
                        <th>ID</th>
                        {/*<th></th>*/}
                    </TableRow>
                    </thead>
                    <tbody style={{width: "100%"}}>
                    {
                        a.map(exam => {
                            return <>
                                <TableRow>
                                    <td>{exam.date}</td>
                                    <td>{exam.courseCode}</td>
                                    <td>{exam.examType}</td>
                                    <td>{exam.formula}</td>
                                    <td>{exam.id}</td>
                                    {/*<td> &nbsp; <input type="checkbox"/> &nbsp;  </td>*/}
                                </TableRow>
                            </>
                        })
                    }
                    </tbody>
                </table>
            </div>

            <form className="text-field-group">
                <div className="form-group">
                    <label htmlFor="Data">Data Examen &nbsp;</label>
                    <input type="text" id="Data" name="Data"/>
                </div>

                <div className="form-group">
                    <label htmlFor="CodCurs">Cod Curs &nbsp;</label>
                    <input type="text" id="CodCurs" name="CodCurs"/>
                </div>

                <div className="form-group">
                    <label htmlFor="TipExamen">Tip Examen &nbsp;</label>
                    <select id="TipExamen" name="TipExamen">
                        <option value="colocviu">Colocviu</option>
                        <option value="inSesiune">In sesiune</option>
                    </select>
                </div>

                <div className="form-group">
                    <label htmlFor="Formula">Formatie &nbsp;</label>
                    <input type="text" id="Formula" name="Formula"/>
                </div>

                <div className="form-group">
                    <label htmlFor="Id">Id &nbsp;</label>
                    <input type="text" id="Id" name="Id"/>
                </div>
            </form>
            <div className="button-group-examen">
                <button onClick={addExam} className="button-examen-1">Adauga</button>
                <button onClick={removeExam} className="button-examen-2">Sterge</button>
                <button onClick={updateExam} className="button-examen-3">Modifica</button>
            </div>
        </div>
    );
}
