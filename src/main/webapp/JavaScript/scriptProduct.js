const utilsProduct = {
    createForm: true,
    Products: [],
    Count: function (data) {
        let name = 'Product';
        counter = $('#counter');
        if (data) {
            if (data > 1)
                name = 'Products';

            counter.html(data + ' ' + name);
        } else
            counter.html('No ' + name);
    },
    FetchAllProducts: function () {
        $.get('/api/products').done((data) => {
            this.Products = data.sort((a, b) => (a.productId > b.productId) ? 1 : -1);
            this.renderProducts(data);
        })
    },
    removeWithId: function (id) {
        $.ajax({
            url: `/api/products/${id}`,
            type: 'DELETE',
            dataType: 'text',
            success: () => {
                $(`#Product${id}`).remove();
                console.log("Deleted!, fetching now");
                this.FetchAllProducts();
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
            $("#productIdInput").prop('disabled', false);
        }else{
            this.createForm = false;
            this.renderInputFields(id)
            $("#productIdInput").prop('disabled', true);
            $("#modalText").text(`Update ${name}, #${id}`);
            $('#createProductModal').modal();
        }
    },
    createProduct: function(){
        let _this = this;
        $.ajax({
            type: "POST",
            url: "/api/products",
            data: JSON.stringify(getFormData($('#inputForm'))),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function(data){
                $('#createProductModal').modal('toggle');
                document.getElementById("inputForm").reset();
                _this.FetchAllProducts();
            },
            failure: function(errMsg) {
                alert(errMsg);
                console.error(errMsg);
            }
        });
    },
    updateProduct: function(){
        let _this = this;
        $("#productIdInput").prop('disabled', false);
        $.ajax({
            type: "PUT",
            url: "/api/products",
            data: JSON.stringify(getFormData($('#inputForm'))),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function(data){
                $('#createProductModal').modal('toggle');
                document.getElementById("inputForm").reset();
                _this.FetchAllProducts();
            },
            failure: function(errMsg) {
                alert(errMsg);
                console.error(errMsg);
            }
        });
        $("#productIdInput").prop('disabled', true);

    }

}


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


