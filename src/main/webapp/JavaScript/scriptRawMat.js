const utils = {
    createForm: true,
    RawMaterials: [],
    Count: function (data) {
        let name = 'RawMaterial';
        counter = $('#counter');
        if (data) {
            if (data > 1)
                name = 'RawMaterials';

            counter.html(data + ' ' + name);
        } else
            counter.html('No ' + name);
    },
    FetchAllRawMaterials: function () {
        $.get('/api/RawMaterials').done((data) => {
            this.RawMaterials = data.sort((a, b) => (a.rawMatId > b.rawMatId) ? 1 : -1);
            this.renderRawMaterials(data);
        })
    },

    removeWithId: function (id) {
        $.ajax({
            url: `/api/RawMaterials/${id}`,
            type: 'DELETE',
            dataType: 'text',
            success: () => {
                $(`#RawMaterial${id}`).remove();
                console.log("Deleted!, fetching now");
                this.FetchAllRawMaterials();
            }
        });
    },
    closebtn: function (el) {
        let id = el.dataset.id;
        let name = el.dataset.name;
        let resp = confirm(`Are you sure you want to delete ${name} with id ${id}?`)
        if (resp) {
            this.removeWithId(id);
        }
    },
    setForm: function(status, id, name) {
        id = id || 0;
        name = name || "";
        if(status === "create" && id === 0){
            this.createForm = true;
            document.getElementById("inputForm").reset();
            $("#modalText").text("Create");
            $("#rawMatInput").prop('disabled', false);
        }else{
            this.createForm = false;
            this.renderInputFields(id)
            $("#rawMatInput").prop('disabled', true);
            $("#modalText").text(`Update ${name}, #${id}`);
            $('#createRawMatModal').modal();
        }
    },
    createRawMaterial: function(){
        let _this = this;
        $.ajax({
            type: "POST",
            url: "/api/RawMaterials",
            data: JSON.stringify(getFormData($('#inputForm'))),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function(data){
                $('#createRawMatModal').modal('toggle');
                document.getElementById("inputForm").reset();
                _this.FetchAllRawMaterials();
            },
            failure: function(errMsg) {
                alert(errMsg);
                console.error(errMsg);
            }
        });
    },
    updateRawMaterial: function(){
        let _this = this;
        $("#rawMatInput").prop('disabled', false);
        $.ajax({
            type: "PUT",
            url: "/api/RawMaterials",
            data: JSON.stringify(getFormData($('#inputForm'))),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function(data){
                $('#createRawMatModal').modal('toggle');
                document.getElementById("inputForm").reset();
                _this.FetchAllRawMaterials();
            },
            failure: function(errMsg) {
                alert(errMsg);
                console.error(errMsg);
            }
        });
        $("#rawMatInput").prop('disabled', true);

    }

}

const app = Object.assign(utils, renders);

$(document).ready(() => {

    Particles.init({ selector: '.background', maxParticles: 250, connectParticles: true, minDistance: 150, speed: 0.35, color: '#CECECE' });
    app.RawMatEl = $('#RawMaterial');
    app.FetchAllRawMaterials();
    $('#submitForm').on('click', (event) => {
        event.preventDefault();
        if(app.createForm)
            app.createRawMaterial();
        else
            app.updateRawMaterial();
    });
})


/* Fix this */
function getFormData($form){
    var unindexed_array = $form.serializeArray();
    var indexed_array = {roles: []};

    $.map(unindexed_array, function(n, i){
        if(n['name'] == "roles"){
            indexed_array[n['name']].push(n['value']);
        }else{
            indexed_array[n['name']] = n['value'];
        }
    });
    return indexed_array;
}


