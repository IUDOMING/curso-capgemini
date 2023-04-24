import React, { Component } from "react";
import { ValidationMessage, ErrorMessage, Esperando, PaginacionCmd as Paginacion,} from "../biblioteca/comunes";
import { titleCase } from "../biblioteca/formateadores";
export class FilmsMnt extends Component {
  constructor(props) {
    super(props);
    this.state = {
      modo: "list",
      listado: null,
      listadoCat: null,
      elemento: null,
      error: null,
      loading: true,
      pagina: 0,
      paginas: 0,
    };
    this.idOriginal = null;
    this.url =(process.env.REACT_APP_API_URL || "http://localhost:8003/") +"/api/peliculas/v1";
  }

  setError(msg) {
    this.setState({ error: msg, loading: false });
  }

  list(num) {
    let pagina = this.state.pagina;
    if (num || num === 0) pagina = num;
    this.setState({ loading: true });
    fetch(`${this.url}?page=${pagina}&size=30`)
      .then((response) => {
        response.json().then(
          response.ok
            ? (data) => {
                this.setState({
                  modo: "list",
                  listado: data.content,
                  loading: false,
                  pagina: data.number,
                  paginas: data.totalPages,
                });
              }
            : (error) => this.setError(`${error.status}: ${error.error}`)
        );
      })
  }

  add() {
    this.setState({
      modo: "add",
      elemento: {
        filmId: 0,
        title: "",        
    }
    });
  }
  edit(key) {
    this.setState({ loading: true });
    fetch(`${this.url}/${key}`)
      .then((response) => {
        response.json().then(
          response.ok
            ? (data) => {
                this.setState({
                  modo: "edit",
                  elemento: data,
                  loading: false,
                });
                this.idOriginal = key;
              }
            : (error) => this.setError(`${error.status}: ${error.error}`)
        );
      })
      .catch((error) => this.setError(error));
  }
  view(key) {
    this.setState({ loading: true });
    fetch(`${this.url}/${key}`)
      .then((response) => {
        response.json().then(
          response.ok
            ? (data) => {
                this.setState({
                  modo: "view",
                  elemento: data,
                  loading: false,
                });
              }
            : (error) => this.setError(`${error.status}: ${error.error}`)
        );
      })
      .catch((error) => this.setError(error));
  }
  delete(key) {
    if (!window.confirm("¿Seguro?")) return;
    this.setState({ loading: true });
    fetch(`${this.url}/${key}`, { method: "DELETE" })
      .then((response) => {
        if (response.ok) this.list();
        else
          response.json().then((error) =>
            this.setError(`${error.status}:
    ${error.error}`)
          );
        this.setState({ loading: false });
      })
      .catch((error) => this.setError(error));
  }
  componentDidMount() {
    this.list(0);
  }

  cancel() {
    this.list();
  }
  send(elemento) {
    this.setState({ loading: true });
    // eslint-disable-next-line default-case
    switch (this.state.modo) {
      case "add":
        fetch(`${this.url}`, {
          method: "POST",
          body: JSON.stringify(elemento),
          headers: {
            "Content-Type": "application/json",
          },
        })
          .then((response) => {
            if (response.ok) this.cancel();
            else
              response.json().then((error) =>
                this.setError(`${error.status}:
    ${error.detail}`)
              );
            this.setState({ loading: false });
          })
          .catch((error) => this.setError(error));
        break;
      case "edit":
        fetch(`${this.url}/${this.idOriginal}`, {
          method: "PUT",
          body: JSON.stringify(elemento),
          headers: {
            "Content-Type": "application/json",
          },
        })
          .then((response) => {
            if (response.ok) this.cancel();
            else
              response.json().then((error) =>
                this.setError(`${error.status}:
    ${error.detail}`)
              );
            this.setState({ loading: false });
          })
          .catch((error) => this.setError(error));
        break;
    }
  }

  render() {
    if (this.state.loading) return <Esperando />;
    let result = [
      <ErrorMessage
        key="error"
        msg={this.state.error}
        onClear={() => this.setState({ error: null })}
      />,
    ];
    switch (this.state.modo) {
      case "add":
      case "edit":
        result.push(
          <FilmsForm
            key="main"
            isAdd={this.state.modo === "add"}
            elemento={this.state.elemento}
            onCancel={(e) => this.cancel()}
            onSend={(e) => this.send(e)}
          />
        );
        break;
      case "view":
        result.push(
          <FilmsView
            key="main"
            elemento={this.state.elemento}
            onCancel={(e) => this.cancel()}
          />
        );
        break;
      default:
        if (this.state.listado)
          result.push(
            <FilmsList
              key="main"
              listado={this.state.listado}
              pagina={this.state.pagina}
              paginas={this.state.paginas}
              onAdd={(e) => this.add()}
              onView={(key) => this.view(key)}
              onEdit={(key) => this.edit(key)}
              onDelete={(key) => this.delete(key)}
              onChangePage={(num) => this.list(num)}
            />
          );
        break;
    }
    return result;
  }
}

function FilmsList(props) {
  return (
    <>
      <table className="table table-hover table-striped">
        <thead className="table-info">
          <tr>
            <th>Lista de Películas</th>
            <th className="text-end">
              <input
                type="button"
                className="btn btn-primary"
                value="Añadir"
                onClick={(e) => props.onAdd()}
              />
            </th>
          </tr>
        </thead>
        <tbody className="table-group-divider">
          {props.listado.map((item) => (
            <tr key={item.filmId}>
              <td>{titleCase(item.title)}</td>
              <td className="text-end">
                <div className="btn-group text-end" role="group">
                  <input
                    type="button"
                    className="btn btn-primary"
                    value="Ver"
                    onClick={(e) => props.onView(item.filmId)}
                  />
                  <input
                    type="button"
                    className="btn btn-primary"
                    value="Editar"
                    onClick={(e) => props.onEdit(item.filmId)}
                  />
                  <input
                    type="button"
                    className="btn btn-danger"
                    value="Borrar"
                    onClick={(e) => props.onDelete(item.filmId)}
                  />
                </div>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
      <Paginacion
        actual={props.pagina}
        total={props.paginas}
        onChange={(num) => props.onChangePage(num)}
      />
    </>
  );
}

function FilmsView({ elemento, onCancel }) {
  return (
    <div>
      <p>
        <b>Código:</b> {elemento.filmId}
        <br />
        <b>Titulo:</b> {elemento.title}
        <br />
        <b>Descripción:</b> {elemento.description}
        <br />
        <b>Año de estreno:</b> {elemento.releaseYear}
        <br />
        <b>Idioma:</b> {elemento.language.idioma}
        <br />
        <b>Idioma original:</b> {elemento.languageVO}
        <br />
        <b>Tiempo de alquiler:</b> {elemento.rentalDuration}
        <br />
        <b>Puntuación:</b> {elemento.rentalRate}
        <br />
        <b>Coste de reemplazo:</b> {elemento.replacementCost}
        <br />
        <b>Duración:</b> {elemento.length} min
        <br />
        <b>Rating de edad:</b> {elemento.rating}
        <br />
        <b>Actores:</b> {elemento.actors.map((actor) => <li>{actor}</li>)}
        <br />
        <b>Categoria:</b> {elemento.categories.map((category)=><li>{category}</li>)}
        <br />
      </p>
      <p>
        <button
          className="btn btn-primary"
          type="button"
          onClick={(e) => onCancel()}
        >
          Volver
        </button>
      </p>
    </div>
  );
}

class FilmsForm extends Component {
  constructor(props) {
    super(props);
    this.state = { elemento: props.elemento, msgErr: [], invalid: false };
    this.handleChange = this.handleChange.bind(this);
    this.onSend = () => {
      if (this.props.onSend) this.props.onSend(this.state.elemento);
    };
    this.onCancel = () => {
      if (this.props.onCancel) this.props.onCancel();
    };
  }
  handleChange(event) {
    const cmp = event.target.name;
    const valor = event.target.value;
    this.setState((prev) => {
      prev.elemento[cmp] = valor;
      return { elemento: prev.elemento };
    });
    this.validar();
  }
  validarCntr(cntr) {
    if (cntr.name) {
      // eslint-disable-next-line default-case
      switch (cntr.name) {
        case "surname":
          cntr.setCustomValidity(
            cntr.value !== cntr.value.toUpperCase()
              ? "Debe estar en mayúsculas"
              : ""
          );
          break;
      }
    }
  }
  validar() {
    if (this.form) {
      const errors = {};
      let invalid = false;
      for (var cntr of this.form.elements) {
        if (cntr.name) {
          this.validarCntr(cntr);
          errors[cntr.name] = cntr.validationMessage;
          invalid = invalid || !cntr.validity.valid;
        }
      }
      this.setState({ msgErr: errors, invalid: invalid });
    }
  }
  componentDidMount() {
    this.validar();
  }
    
  render() {
    return (
      <form
        ref={(tag) => {
          this.form = tag;
        }}
      >
        <div className="form-group">
          <label htmlFor="filmId">Código</label>
          <input
            type="number"
            className={"form-control" + (this.props.isAdd ? "" : "-plaintext")}
            id="filmId"
            name="filmId"
            value={this.state.elemento.filmId}
            onChange={this.handleChange}
            required
            readOnly={!this.props.isAdd}
          />
          <ValidationMessage msg={this.state.msgErr.filmId} />
        </div>
        <div className="form-group">
          <label htmlFor="title">Titulo</label>
          <input
            type="text"
            className="form-control"
            id="title"
            name="title"
            value={this.state.elemento.title}
            onChange={this.handleChange}
            required
            minLength="2"
            maxLength="45"
          />
          <ValidationMessage msg={this.state.msgErr.title} />
        </div>
        <div className="form-group">
          <label htmlFor="description">Descripción</label>
          <input
            type="text"
            className="form-control"
            id="description"
            name="description"
            value={this.state.elemento.description}
            onChange={this.handleChange}
            minLength="2"
            maxLength="45"
          />
          <ValidationMessage msg={this.state.msgErr.description} />
        </div>
        <div className="form-group">
          <label htmlFor="releaseYear">Año de estreno</label>
          <input
            type="text"
            className="form-control"
            id="releaseYear"
            name="releaseYear"
            value={this.state.elemento.releaseYear}
            onChange={this.handleChange}
            minLength="2"
            maxLength="45"
          />
          <ValidationMessage msg={this.state.msgErr.releaseYear} />
        </div>
          <div className="form-group">
          <label htmlFor="language">Idioma</label>
          <input
            type="text"
            className="form-control"
            id="language"
            name="language"
            value={this.state.elemento.language.id}
            onChange={this.handleChange}
            minLength="2"
            maxLength="45"
          />
          <ValidationMessage msg={this.state.msgErr.language} />
        </div> 
        <div className="form-group">
          <label htmlFor="languageVO">Idioma original:</label>
          <input
            type="text"
            className="form-control"
            id="languageVO"
            name="languageVO"
            value={this.state.elemento.languageVO}
            onChange={this.handleChange}
            minLength="2"
            maxLength="45"
          />
          <ValidationMessage msg={this.state.msgErr.languageVO} />
        </div>
        <div className="form-group">
          <label htmlFor="rentalDuration">Tiempo de alquiler:</label>
          <input
            type="text"
            className="form-control"
            id="rentalDuration"
            name="rentalDuration"
            value={this.state.elemento.rentalDuration}
            onChange={this.handleChange}
            minLength="2"
            maxLength="45"
          />
          <ValidationMessage msg={this.state.msgErr.rentalRate} />
        </div>
        <div className="form-group">
          <label htmlFor="description">Puntuación:</label>
          <input
            type="text"
            className="form-control"
            id="rentalRate"
            name="rentalRate"
            value={this.state.elemento.rentalRate}
            onChange={this.handleChange}
            minLength="2"
            maxLength="45"
          />
          <ValidationMessage msg={this.state.msgErr.rentalRate} />
        </div>
        <div className="form-group">
          <label htmlFor="replacementCost">Coste de reemplazo:</label>
          <input
            type="text"
            className="form-control"
            id="replacementCost"
            name="replacementCost"
            value={this.state.elemento.replacementCost}
            onChange={this.handleChange}
            minLength="2"
            maxLength="45"
          />
          <ValidationMessage msg={this.state.msgErr.replacementCost} />
        </div>
        <div className="form-group">
          <label htmlFor="length">Duración:</label>
          <input
            type="text"
            className="form-control"
            id="length"
            name="length"
            value={this.state.elemento.length}
            onChange={this.handleChange}
            minLength="2"
            maxLength="45"
          />
          <ValidationMessage msg={this.state.msgErr.length} />
        </div>
        <div className="form-group">
          <label htmlFor="rating">Rating de edad:</label>
          <input
            type="text"
            className="form-control"
            id="rating"
            name="rating"
            value={this.state.elemento.rating}
            onChange={this.handleChange}
            minLength="2"
            maxLength="45"
          />
          <ValidationMessage msg={this.state.msgErr.rating} />
        </div>
         <div className="form-group">
          <label htmlFor="actors">Actores:</label>
          <input
            type="text"
            className="form-control"
            id="actors"
            name="actors"
            value=""
            onChange={this.handleChange}
            minLength="2"
            maxLength="45"
          />
          <ValidationMessage msg={this.state.msgErr.actors} />
          <div className="checkList">
            <div className="list-container">
              {this.state.elemento.actors.map((item, index) => (
               <div key={index}>
               <input value={item} type="checkbox" />
               <span>{item}</span>
             </div>
          ))}
            </div>
          </div>
        </div>
        <div className="form-group">
          <label htmlFor="categories">Categoria:</label>
          <input
            type="text"
            className="form-control"
            id="categories"
            name="categories"
            value=""
            onChange={this.handleChange}
            minLength="2"
            maxLength="45"
          />
          <ValidationMessage msg={this.state.msgErr.categories} />
        <div className="checkList">
            <div className="list-container">
              {this.state.elemento.categories.map((item, index) => (
               <div key={index}>
               <input value={item} type="checkbox" />
               <span>{item}</span>
             </div>
          ))}
            </div>
          </div>
        </div>
        <div className="form-group">
          <button
            className="btn btn-primary"
            type="button"
            disabled={this.state.invalid}
            onClick={this.onSend}
          >
            Enviar
          </button>
          <button
            className="btn btn-primary"
            type="button"
            onClick={this.onCancel}
          >
            Volver
          </button>
        </div>
      </form>
    );
  }
}