const utilsProduct = {
	// her er vores 'state' altsaa det data vi benytter os af i denne del af siden
	createForm: true,
	// de vigtigste ting er disse arrays, foerst er det produkterne fra databasen
	Products: [],
	// der naest er det ingredienserne fra databasen
	IngredientsFromDB: [],
	// til sidst er det et objekt der indeholder aendringer af ingredienserne som bruges til at opdatere
    Ingredients: {}, // Format {"/id/": [/*ingredients as {"rawMatId": "1","nonNetto": "1","tolerance": "3"} */]}
	// denne funktion bruges til at opdatere 'counter' til maengden af elementer, der bliver taget hoejde for ingen
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
	// denne funktion henter alle produkter fra databasen med /api/products endpointet
	FetchAllProducts: function() {
		$.get('/api/products').done((data) => {
			this.Products = data.sort((a, b) => (a.productId > b.productId) ? 1 : -1);
			this.renderProducts(data);
		})
		// som en tilfoejelse henter vi ogsaa alle rawmats
		$.get('/api/rawMat').done((data) => {
			this.IngredientsFromDB = data.sort((a, b) => (a.rawMatID > b.rawMatID) ? 1 : -1);
		})
	},
	// denne funktion fjerner et produkt fra et id
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
	// closebtn er det der sker naar man trykker paa slet knappen paa et produkt
	closebtn: function(el) {
		let id = el.dataset.id;
		let name = el.dataset.name;
		let resp = confirm(`Are you sure you want to delete ${name} with id ${id}?`);
		if (resp) {
			this.removeWithId(id);
		}
	},
	// denne funktion haandtere alle handlinger der har noget med modaler at goere. Altsaa opret, opdater og opdater ingredienser (for produkter)
	setForm: function(status, id, name) {
		id = id || 0;
		name = name || "";
		// logik for opret
		if (status === "create" && id === 0) {
			this.createForm = true;
			document.getElementById("inputFormProduct").reset();
			$("#modalText").text("Create");
			$("#productIdInput").prop('disabled', false);
			$('#createProductModal').modal();

			//logik for addingredient modalen
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

			//logik for opdater ingrediens
		} else {
			this.createForm = false;
			this.renderInputFields(id)
			$("#productIdInput").prop('disabled', true);
			$("#modalText").text(`Update ${name}, #${id}`);
			$('#createProductModal').modal();
		}
	},
	// Funktion til at oprette et produkt ud fra inputformproduct formen. denne laver et HTTP post til /api/products
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
	// ligesom create, men HTTP put for at opdatere et produkt
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

	// Naar man tilfoejer en enkelt ingrediens uden at submitte, sker denne logik
    addIngredient: function(){
		$("#productIdInput2").prop('disabled', false);
        let formData = getFormData($("#inputFormProductIng"));
        $("#productIdInput2").prop('disabled', true);

        var pid = parseInt(formData.productId);

        // efter vi har hentet et produktid, tilfoejer vi ingrediensen i listen af ingredienser for produktet
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

        // vi henter i oevrigt ingrediensens navn for at vise det i ingrediens tabellen
        let ingname = this.IngredientsFromDB.find((x) => x.rawMatID == formData.rawMatID).rawMatName
        this.renderAddIngredient(formData, ingname, pid);

    },

	// ligesom closebtn men for enkelte ingredienser i add ingrediens formen
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
	// hoerer til deleteIngredient, den finder ingrediensen i listen af ingredienser for produktet ved
	// hjaelp af navn amount tolerance og produktid, som til sammen opretter et 'unikt' html id
    removeIngredientWithInfo: function (name, amount, tolerance, prodid) {
	    let obj = this.Ingredients[prodid].find(x => {
	        return x.name == name &&
                x.amount == amount &&
                x.tolerance == tolerance
        })
        this.Ingredients[prodid].splice(obj, 1);
	    $(`#ing${name + amount + tolerance}`).remove();

    },

	// naar man submitter sine ingredienser for et produkt, samler vi ingredienserne og udfoerer en HTTP put paa /api/products
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


/* funktion til at hente data fra en html form/ */
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


