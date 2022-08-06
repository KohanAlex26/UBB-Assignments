import A from "../CSS/App.module.css";
import "../CSS/Registration.css";
import React, {useEffect, useState} from "react";

export default function Signup() {
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [registrationNumber, setRegistrationNumber] = useState('');
    const [semigroup, setSemigroup] = useState(false);
    const [studentHousingName, setStudentHousingName] = useState('');
    const [roomNumber, setRoomNumber] = useState('');
    const [error, setError] = useState(null);

    const handleSubmit = (e) => {
        e.preventDefault();
        const newStudent = { email, firstName, lastName, password, registrationNumber, semigroup, studentHousingName, roomNumber};
        console.log(newStudent);
        fetch('http://localhost:8080/api/register', {
            method: 'POST',
            headers: { "Content-Type": "application/json"},
            body:JSON.stringify(newStudent)
        }).then(res => {
            console.log(res);
            if(!res.ok){
                throw Error("could not fetch the data");
            }
            window.location ='/login';
            setError(null);
            return res.json();
        }).catch(err => {
            setError(err.message);
        })

    }
    return (
        <div className={A.Login} >
            <form className="formStyle">
                    <h7><center>Cont nou</center></h7>
                    <h7> Prenume:
                        <input className="inputStyleR"
                               type="text"
                               required
                               onChange={(e) => setFirstName(e.target.value)}
                               value={firstName}/></h7>
                    <h7>Nume:
                        <input className="inputStyleR"
                               type="text"
                               required
                               onChange={(e) => setLastName(e.target.value)}
                               value={lastName} /></h7>
                    <h7>E-mail:
                        <input className="inputStyleR"
                               type="text"
                               required
                               onChange={(e) => setEmail(e.target.value)}
                               value={email} /></h7>
                    <h7>Numar matricol:
                        <input className="inputStyleR"
                               type="text"
                               required
                               onChange={(e) => setRegistrationNumber(e.target.value)}
                               value={registrationNumber}/></h7>
                    <h7>Camin:
                        <input className="inputStyleR"
                               type="text"
                               onChange={(e) => setStudentHousingName(e.target.value)}
                               value={studentHousingName}/></h7>
                    <h7>Numarul camerei:
                        <input className="inputStyleR"
                               type="text"
                               onChange={(e) => setRoomNumber(e.target.value)}
                               value={roomNumber}/></h7>
                    <h7>Semigrupa:
                        <div className="inputStyleR">
                            <select className="dropdown" onChange={(e) => setSemigroup(e.target.value)}
                                    value={semigroup}>
                            <option className="inputStyleR" value="false">1</option>
                            <option className="inputStyleR" value="true">2</option>
                            </select>
                        </div>
                    </h7>
                    <h7>Parola:
                        <input className="inputStyleR"
                               type="password"
                               required
                               onChange={(e) => setPassword(e.target.value)}
                               value={password} /></h7>
                <div>
                    <button className="buttonsLoginStyle" onClick={handleSubmit}>Submit</button>
                </div>
            </form>
            <div>
                <input
                    className="termsStyle"
                    type="submit"
                    value="Termeni si Conditii"
                    onClick={() => alert("termeni si conditii")}
                />
                <input
                    className="contactStyle"
                    type="submit"
                    value="Contact"
                    onClick={() => alert("date de contact")}
                />
            </div>
        </div>
    );
}
