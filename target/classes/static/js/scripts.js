const actorSelected= (select)=>
{
    let index= select.selectedIndex;
    let option=select.options[index];
    let id=option.value;
    let nombre=option.text;
    let urlImagen = option.dataset.url;

    option.disabled = "disabled";
    select.selectedIndex=0;

    agregarActor(id,nombre,urlImagen)

    let ids=$("#ids").val();
    if(ids=="")
    {
        console.log("entro");
        console.log(document.querySelector("#ids"));
        $("#ids").val(id);
    }else{
        $("#ids").val(ids+","+id);
    }
}

function agregarActor(id,nombre,urlImagen)
{
	let htmlString = `
		<div class="card col-md-3 m-2" style="width: 10rem">
			<img src="{URL-IMAGEN}" class="card-img-top">
			<div class="card-body">
				<p class="card-text">{NOMBRE}</p>
				<button type="button" class="btn btn-danger" data-id="{ID}" onclick="eliminarActor(this); return false; return false;"> Eliminar </button>
			</div>
		</div>`;


    htmlString = htmlString.replace("{URL-IMAGEN}",urlImagen );
    htmlString = htmlString.replace("{NOMBRE}",nombre );
    htmlString = htmlString.replace("{ID}",id );
    console.log(urlImagen);
    $("#protagonistas_container").append(htmlString);
}

function eliminarActor(btn)
{
    let id= btn.dataset.id;
    let node = btn.parentElement.parentElement;
    let arraysIds= $("#ids").val().split(",").filter(idActor=>idActor!=id);//Convierte a array
    $("#ids").val(arraysIds.join(","));//Convierte a string
    $("protagonistas option[value=']"+id+"']").prop("disabled",false);
    $(node).remove();
}