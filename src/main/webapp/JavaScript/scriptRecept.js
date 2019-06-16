const utils = {
    createForm: true,
    Recepts: [],
    Count: function (data) {
        let name = 'Recept';
        counter = $('#counter');
        if (data) {
            if (data > 1)
                name = 'Recepts';

            counter.html(data + ' ' + name);
        } else
            counter.html('No ' + name);
    },
    FetchAllRecepts: function () {
        $.get('/api/Recepts').done((data) => {
            this.Recepts = data.sort((a, b) => (a.receptId > b.receptId) ? 1 : -1);
            this.renderRecepts(data);
        })
    },
    removeWithId: function (id) {
        $.ajax({
            url: `/api/Recepts/${id}`,
            type: 'DELETE',
            dataType: 'text',
            success: () => {
                $(`#Recept${id}`).remove();
                console.log("Deleted!, fetching now");
                this.FetchAllRecepts();
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
            $("#receptIdInput").prop('disabled', false);
        }else{
            this.createForm = false;
            this.renderInputFields(id)
            $("#receptIdInput").prop('disabled', true);
            $("#modalText").text(`Update ${name}, #${id}`);
            $('#createReceptModal').modal();
        }
    },
    createRecept: function(){
        let _this = this;
        $.ajax({
            type: "POST",
            url: "/api/Recepts",
            data: JSON.stringify(getFormData($('#inputForm'))),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function(data){
                $('#createReceptModal').modal('toggle');
                document.getElementById("inputForm").reset();
                _this.FetchAllRecepts();
            },
            failure: function(errMsg) {
                alert(errMsg);
                console.error(errMsg);
            }
        });
    },
    updateRecept: function(){
        let _this = this;
        $("#receptIdInput").prop('disabled', false);
        $.ajax({
            type: "PUT",
            url: "/api/Recepts",
            data: JSON.stringify(getFormData($('#inputForm'))),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function(data){
                $('#createReceptModal').modal('toggle');
                document.getElementById("inputForm").reset();
                _this.FetchAllRecepts();
            },
            failure: function(errMsg) {
                alert(errMsg);
                console.error(errMsg);
            }
        });
        $("#receptIdInput").prop('disabled', true);

    }

}

const app = Object.assign(utils, renders);

$(document).ready(() => {

    Particles.init({ selector: '.background', maxParticles: 250, connectParticles: true, minDistance: 150, speed: 0.35, color: '#CECECE' });
    app.ReceptEL = $('#Recept');

    app.FetchAllRecepts();

    $('#submitForm').on('click', (event) => {
        event.preventDefault();
        if(app.createForm)
            app.createRecept();
        else
            app.updateRecept();
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


