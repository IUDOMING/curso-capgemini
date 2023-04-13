console.log('Soy el fichero en node')
x = 'En demo'
a='5s'
let b=2
c= a = b = 3
var imprimir = false;
// console.log(`-------> ${a+b}`)

function kk() {
    let a = '7'
    if(true) {
        let x = 'bloque'
    }
    d = a + x;
    console.log(`-------> ${d}`)
}
kk();

console.log(`-------> fuera ${d}`)

function usaKK() {
    kk();
}
// if(a=1) {
//     console.log(`Cierto`)
// }
// inprimir = true
// if(imprimir) {
//     console.log(`Cierto`)
// }

t = [10, 20, 30]
//t = { x: 10, y: 20}
// for(v in t) {
//     console.log(t[v])
// }
for(v of t) {
    console.log(v)
}
// cmp = 'x'
// t[cmp]
// t.x === t['x']

let cond = 0;

console.log(cond ? `Cierto` : `Falso`)

function avg ()
{var rlst =0;
    for (var i=0; i< arguments.length; i++)
    {
        rlst += arguments[i];

    }
    return arguments.length ? (rlst / arguments.length) : 0;

}

console.log(avg(10,20,30))
console.log(avg(...t))

punto = { x: 10, y: 20}
function coordenadas(x, y)
{
    return x+y;
}

coordenadas = (x,y) => x+y;
console.log(coordenadas(punto.x, punto.y))

// console.log(coordenadas(...punto))

function suma(a, b) { return a+b;}
//Expresión Lambda(Expresión flecha en JS)
//Return implicito
//Es lo mismo que la sintáxis de Function
suma = (a, b) => a+b;
t.filter(item => item % 2)
t.filter(item => item > 10)
filtrado = function (item) {return item>10;}


//Arrays

for (let i=0; i<10; i++)
    t.filter(filtrado);


let tab = new Array();
tab = [10, 20, 30];
tab[10]='otro'
tab[5] = (a, b) => a+b;
//Añade en el úlitmo lugar (creando un nuevo indice) lo que se le ha pasado
tab.push('añade')
//Elimina desde el punto indicado en adelante
tab.splice(10)
for (v in tab)
{
    console.log(v)
}

for (v of tab)
{
    console.log(v)
}


console.log(tab[5](2,3))

let o = new Object();
//Manera rápida de crear un array asociativo de objetos
o = {}
o.nombre = 'Pepito'
o['apellido']= 'Grillo'
o.nom = () => this.nombre + ' ' + this.apellido;
p = { nombre: 'caramelo', apellido: 'limón', nom : () => this.nombre + ' ' + this.apellido }
o=p
console.log(`${o.nombre} ${o.apellido} ${o.nom()} `)