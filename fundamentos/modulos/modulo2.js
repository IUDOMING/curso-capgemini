let name = 'Modulo'

function resta(a,b){
    return a-b;
}

function calc(a,b){
    return resta(a,b);
}

function display() {
console.log([`Soy ${name}`])
}

export{name, calc, display}