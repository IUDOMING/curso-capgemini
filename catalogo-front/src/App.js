import logo from './logo.svg';
import './App.css';


import React, { Component } from 'react'
import { ErrorBoundary } from './comunes';
import { ActorsMnt } from './componentes/actores';
import { CategoriesMnt } from './componentes/categorias';
import { LanguagesMnt } from './componentes/lenguajes';
import { FilmsMnt } from './componentes/films';

export default class App extends Component {
  constructor(props) {
    super(props)
    this.state = {
      cont: 0,
      main: 0
    }
    this.menu = [
      { texto: 'Inicio', url: '/', componente: <Home /> },
      { texto: 'Peliculas', url: '/peliculas', componente: <FilmsMnt /> },
      { texto: 'Lenguages', url: '/lenguages', componente: <LanguagesMnt /> },
      { texto: 'Categorias', url: '/categorias', componente: <CategoriesMnt /> },
      { texto: 'Actores', url: '/actores', componente: <ActorsMnt /> },
    ]
  }

  render() {
    return (
      <>
        <Cabecera menu={this.menu} actual={this.state.main} onSelectMenu={indice => this.setState({ main: indice })} />
        <main className='container-fluid'>
          <ErrorBoundary>
            {this.menu[this.state.main].componente}
          </ErrorBoundary>
        </main>
        <Pie />
      </>
    )
  }
}

function Cabecera(props) {
  return (
    <header>
      <nav className="navbar navbar-expand-lg bg-body-tertiary">
        <div className="container-fluid">
          <a className="navbar-brand" href="#">
          </a>
          <button
            className="navbar-toggler"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent"
            aria-expanded="false"
            aria-label="Toggle navigation"
          >
            <span className="navbar-toggler-icon" />
          </button>
          <div className="collapse navbar-collapse" id="navbarSupportedContent">
            <Menu {...props} />
            <Buscar />
          </div>
        </div>
      </nav>

    </header>
  );
}

function Menu({ menu, actual, onSelectMenu }) {
  return (
    <ul className="navbar-nav me-auto mb-2 mb-lg-0">
      {menu.map((item, index) =>
        <li key={index} className="nav-item">
          <a className={'nav-link' + (actual === index ? ' active' : '')} aria-current="page" href="."
            onClick={ev => { 
              ev.preventDefault()
              onSelectMenu && onSelectMenu(index) 
            }}>{item.texto}</a>
        </li>
      )
      }
    </ul>
  );
}

function Buscar() {
  return (
    <form className="d-flex" role="search">
    <input
      className="form-control me-2"
      type="search"
      placeholder="Search"
      aria-label="Search"
    />
    <button className="btn btn-outline-success" type="submit">
      Search
    </button>
  </form>
)
}
function Pie() {
  return null;
}


function Home() {
  let url = process.env.REACT_APP_API_URL

  return (
    // eslint-disable-next-line jsx-quotes
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <h1>Hola mundo</h1>
        <h2>url: {url}</h2>
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
