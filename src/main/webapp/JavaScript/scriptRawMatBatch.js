const utils = {
    createForm: true,
    RawMaterialBatches: [],
    Count: function (data) {
        let name = 'RawMaterialBatch';
        counter = $('#counter');
        if (data) {
            if (data > 1)
                name = 'RawMaterialBatches';

            counter.html(data + ' ' + name);
        } else
            counter.html('No ' + name);
    },
    FetchAllRawMaterialBatches: function () {
        $.get('/api/RawMaterialBatches').done((data) => {
            this.RawMaterialBatches = data.sort((a, b) => (a.RMBId > b.RMBId) ? 1 : -1);
            this.renderRawMaterialBatches(data);
        })
    },

    removeWithId: function (id) {
        $.ajax({
            url: `/api/RawMaterialBatches/${id}`,
            type: 'DELETE',
            dataType: 'text',
            success: () => {
                $(`#RawMaterialBatch${id}`).remove();
                console.log("Deleted!, fetching now");
                this.FetchAllRawMaterialBatches();
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
            $("#RMBIdInput").prop('disabled', false);
        }else{
            this.createForm = false;
            this.renderInputFields(id)
            $("#RMBIdInput").prop('disabled', true);
            $("#modalText").text(`Update ${name}, #${id}`);
            $('#createRawMatBatchModal').modal();
        }
    },
    createRawMaterialBatch: function(){
        let _this = this;
        $.ajax({
            type: "POST",
            url: "/api/RawMaterialBatches",
            data: JSON.stringify(getFormData($('#inputForm'))),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function(data){
                $('#createRawMatBatchModal').modal('toggle');
                document.getElementById("inputForm").reset();
                _this.FetchAllRawMaterialBatches();
            },
            failure: function(errMsg) {
                alert(errMsg);
                console.error(errMsg);
            }
        });
    },
    updateRawMaterialBatch: function(){
        let _this = this;
        $("#RMBIdInput").prop('disabled', false);
        $.ajax({
            type: "PUT",
            url: "/api/RawMaterialBatches",
            data: JSON.stringify(getFormData($('#inputForm'))),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function(data){
                $('#createRawMatBatchModal').modal('toggle');
                document.getElementById("inputForm").reset();
                _this.FetchAllRawMaterialBatches();
            },
            failure: function(errMsg) {
                alert(errMsg);
                console.error(errMsg);
            }
        });
        $("#RMBIdInput").prop('disabled', true);

    }

}

const app = Object.assign(utils, renders);

$(document).ready(() => {

    Particles.init({ selector: '.background', maxParticles: 250, connectParticles: true, minDistance: 150, speed: 0.35, color: '#CECECE' });
    app.RMBEl = $('#RawMaterialBatch');
    app.FetchAllRawMaterialBatches();
    $('#submitForm').on('click', (event) => {
        event.preventDefault();
        if(app.createForm)
            app.createRawMaterialBatch();
        else
            app.updateRawMaterialBatch();
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


