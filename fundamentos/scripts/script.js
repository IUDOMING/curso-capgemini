val1=null;
function calculate() {
    var p = document.getElementById("result").value;
    var q = eval(p);
    document.getElementById("result").value = q;
}

function suma () {
}
function resta () {
}        
function division () {
}
function multiplicacion () {
}    
function addTip() {
    let tip = document.querySelectorAll('input[type=button]');
    tip.forEach((item) => {
    item.addEventListener('click', (event) => {
        if(item.value=='C'){
            document.getElementById('result').value = '';
        }
        else if(item.value=='='){
            calculate();
        }else{
            document.getElementById('result').value += item.value;
        }
                                             });
                         });
                  }
addTip();

