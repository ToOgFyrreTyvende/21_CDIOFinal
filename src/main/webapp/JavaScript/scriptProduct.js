const utilsProduct = {
	createForm: true,
	Products: [],
	IngredientsFromDB: [],
    Ingredients: {}, // Format {"/id/": [/*ingredients as {"rawMatId": "1","nonNetto": "1","tolerance": "3"} */]}
	Count: function(data) {
		let name = 'Product';
		counter = $('#counterProd');
		if (data) {
			if (data > 1)
				name = 'Products';

			counter.html(data + ' ' + name);
		} else
			counter.html('No ' + name);
	},
	FetchAllProducts: function() {
		$.get('/api/products').done((data) => {
			this.Products = data.sort((a, b) => (a.productId > b.productId) ? 1 : -1);
			this.renderProducts(data);
		})

		$.get('/api/rawMat').done((data) => {
			this.IngredientsFromDB = data.sort((a, b) => (a.rawMatID > b.rawMatID) ? 1 : -1);
		})
	},
	removeWithId: function(id) {
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
	closebtn: function(el) {
		let id = el.dataset.id;
		let name = el.dataset.name;
		let resp = confirm(`Are you sure you want to delete ${name} with id ${id}?`);
		if (resp) {
			this.removeWithId(id);
		}
	},
	setForm: function(status, id, name) {
		id = id || 0;
		name = name || "";
		if (status === "create" && id === 0) {
			this.createForm = true;
			document.getElementById("inputForm").reset();
			$("#modalText").text("Create");
			$("#productIdInput").prop('disabled', false);
			$('#createProductModal').modal();

		} else if (status === "addIngredient") {
			this.createForm = true;
			$('#ingredientFormTable').empty();
            let product = this.Products.find((x) => x.productId == id);
            this.renderInputFieldsIngredients(id);
            this.renderIngredientsTable(product);
			$("#modalText").text("Update");
            this.Ingredients[id] = [];
            this.Ingredients[id] = [...product.ingredients];
			$("#productIdInput2").prop('disabled', true);
			$('#manageProductModal').modal();
		} else {
			this.createForm = false;
			this.renderInputFields(id)
			$("#productIdInput").prop('disabled', true);
			$("#modalText").text(`Update ${name}, #${id}`);
			$('#createProductModal').modal();
		}
	},
	createProduct: function() {
		let _this = this;
		$.ajax({
			type: "POST",
			url: "/api/products",
			data: JSON.stringify(getFormData($('#inputFormProduct'))),
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			success: function(data) {
				$('#createProductModal').modal('toggle');
				document.getElementById("inputFormProduct").reset();
				_this.FetchAllProducts();
			},
			failure: function(errMsg) {
				alert(errMsg);
				console.error(errMsg);
			}
		});
	},
	updateProduct: function() {
		let _this = this;
		$("#productIdInput").prop('disabled', false);
		$.ajax({
			type: "PUT",
			url: "/api/products",
			data: JSON.stringify(getFormData($('#inputFormProduct'))),
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			success: function(data) {
				$('#createProductModal').modal('toggle');
				document.getElementById("inputFormProduct").reset();
				_this.FetchAllProducts();
			},
			failure: function(errMsg) {
				alert(errMsg);
				console.error(errMsg);
			}
		});
		$("#productIdInput").prop('disabled', true);
	},

    addIngredient: function(){
		$("#productIdInput2").prop('disabled', false);
        let formData = getFormData($("#inputFormProductIng"));
        $("#productIdInput2").prop('disabled', true);

        var pid = parseInt(formData.productId);
        ;

        if(this.Ingredients[pid] === undefined)
            this.Ingredients[pid] = [];
        let el = {
            "rawMatId": parseInt(formData.rawMatID),
            "amount": parseFloat(formData.amount),
            "tolerance": parseFloat(formData.tolerance),
            "prodIngId": 0,
            "name":"name"
        };
        this.Ingredients[pid].push(el);

        let ingname = this.IngredientsFromDB.find((x) => x.rawMatID == formData.rawMatID).rawMatName
        this.renderAddIngredient(formData, ingname, pid);

    },

    deleteIngredient: function (el){
        let prodid = el.dataset.prodid;
        let name = el.dataset.name;
        let amount = el.dataset.amount;
        let tolerance = el.dataset.tolerance;
        let resp = confirm(`Are you sure you want to delete ${name} with amount ${amount}?`);
        if (resp) {
            this.removeIngredientWithInfo(name, amount, tolerance, prodid);
        }
    },
    removeIngredientWithInfo: function (name, amount, tolerance, prodid) {
	    let obj = this.Ingredients[prodid].find(x => {
	        return x.name == name &&
                x.amount == amount &&
                x.tolerance == tolerance
        })
        this.Ingredients[prodid].splice(obj, 1);
	    $(`#ing${name + amount + tolerance}`).remove();

    },


    submitIngredients: function(){
	    let id = $("#productIdInput2").val();
	    let prod = this.Products.find((x) => x.productId == id )

        var objToSend = {...prod};
	    objToSend.ingredients = [];
        this.Ingredients[id].map((x) => {
            objToSend.ingredients.push(x);
        });
		let _this = this;

		$.ajax({
            type: "PUT",
            url: "/api/products",
            data: JSON.stringify(objToSend),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function(data) {
                $('#manageProductModal').modal('toggle');
				_this.FetchAllProducts();
            },
            failure: function(errMsg) {
                alert(errMsg);
                console.error(errMsg);
            }
        });
	}


}


/* Fix this */
function getFormData($form) {
	var unindexed_array = $form.serializeArray();
	var indexed_array = {};

	$.map(unindexed_array, function(n, i) {
		if (n['name'] == "roles") {
			indexed_array[n['name']].push(n['value']);
		} else {
			indexed_array[n['name']] = n['value'];
		}
	});
	return indexed_array;
}


