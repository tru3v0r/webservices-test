var traiterReponse = function (response){
//response ici au format "json string"
var zoneResultat = document.getElementById("spanRes");
var jsDevise = JSON.parse(response);
zoneResultat.innerHTML=jsDevise.value; //ou .rate
}
function onSearchDevise(){
var zoneSaisieCode = document.getElementById("txtCodeDevise");
var codeDevise = zoneSaisieCode.value;
console.log("codeDevise="+codeDevise);
var urlWsGet="./currency-api/public/currency/"+codeDevise;
makeAjaxGetRequest(urlWsGet,traiterReponse); //non bloquant (asynchrone)
//....
}