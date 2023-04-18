import logo from "./logo.svg";
import "./App.css";

import React, { Component } from "react";
import { Contador } from "./Componentes";

export default class App extends Component {
  render() {
    return (
      <>
        <main className="container-fluid">
          <Contador init={10} delta={2} />
        </main>
      </>
    );
  }
}

class DemosJSX extends Component {
  render() {
    let nombre = "mundo1";
    let estilo = "App-link";
    let saluda = <h1>Hola {nombre.toUpperCase() + "!"}!!</h1>;
    let dim = { width: 100, height: 50 };
    let errorStyle = { color: "white", backgroundColor: "red" };
    let limpia = true;
    let falsa;
    let lista = [
      { id: 1, nombre: "Madrid" },
      { id: 2, nombre: "Barcelona" },
      { id: 3, nombre: "Sevilla" },
      { id: 4, nombre: "Valencia" },
    ];

    return (
      <>
        {saluda}
        {limpia ? <b>verdaderao</b> : <i>falso {nombre}</i>}
        {limpia && <h2>Limpia</h2>}
        {falsa ?? <b>no existe</b>}
        {falsa?.persona?.nombre}
        <div style={{ color: "white", backgroundColor: "red" }}>DemosJSX</div>
        <h2 className={estilo} style={errorStyle}>
          Hola {nombre}
        </h2>
        <ul>
          {" "}
          {[1, 2, 3, 4, 3, 2, 1].map((item, index) => (
            <li key={index}>Elemento {item}</li>
          ))}
        </ul>
        <select>
          {lista.map((item) => (
            <option key={item.id} value={item.id}>
              {item.nombre}
            </option>
          ))}
        </select>
        <img
          src={logo}
          className="App-logo"
          alt="logo"
          {...dim}
          hidden={false}
        />
      </>
    );
  }
}

function Home() {
  return (
    // eslint-disable-next-line jsx-quotes
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <h1>Hola mundo</h1>
        <h2>url: {process.env.REACT_APP_API_URL}</h2>
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>
    </div>
  );
}
