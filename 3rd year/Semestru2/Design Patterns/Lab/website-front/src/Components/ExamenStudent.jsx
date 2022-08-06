import "../CSS/ExamStudent.css";
import NavBar from "../Components/Navbar.jsx";
import A from "../CSS/App.module.css"
import {useEffect, useState} from "react";
import {render} from "react-dom";

export default function ExamStudent() {

    const [a, setA] = useState([])
    const columns = [
        "Data",
        "Nume Materie",
        "Tip Examen",
        "Formatie"
    ]
    useEffect(async () => {
        await getData()
    }, [])

    const getData = async () => {
        fetch('http://localhost:8080/api/exams/136', {
            method: 'GET',
        }).then(r => r.json()).then(data => setA(data))
    }

    return (
        <div className={A.App}>
            <NavBar/>
            <div id="table-container-exam-student">
                <table className="styled-table-exam-student">
                    <thead className="exam-table-head-student">
                    {columns.map(column => {
                        return (
                            <th>
                                {column}
                            </th>
                        )
                    })}
                    </thead>
                    <tbody className="table-body-exam-student">
                    {
                        a.map(exam => {
                            return <>
                                <tr>
                                    <td>{exam.date}</td>
                                    <td>{exam.courseName}</td>
                                    <td>{exam.examType}</td>
                                    <td>{exam.formula}</td>
                                </tr>
                            </>
                        })                    }

                    </tbody>
                </table>
            </div>
        </div>
    );

}