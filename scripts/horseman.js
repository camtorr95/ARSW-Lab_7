var path = require('path');

var Horseman = require('node-horseman');
var horseman = new Horseman();

var name = process.argv[2];
var letter = process.argv[3];
var wait1 = parseInt(process.argv[4]);
var wait2 = parseInt(process.argv[5]);
var expected = process.argv[6];

horseman
  .open('http://localhost:8080')
  .type('input[id="gameid"]', '1')
  .type('input[id="name"]', name)
  .click("button:contains('Ingresar a partida')")
  .wait(wait1)
  .type('input[id="caracter"]', letter)
  .click("button:contains('Agregar una letra')")
  .wait(wait2)
  .screenshot(path.resolve(__dirname, name+'.png'))
  .evaluate(function(){return $('#palabra').html()})
  .then(function(pal){
    console.info(name+' word:'+pal);
    
    })  
  .finally(function(){    
                horseman.close();
            });
