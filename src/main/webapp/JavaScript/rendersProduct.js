const rendersProduct = {
	// Products tabellen som bliver vist paa skaermen  bliver oprettet her, efter vi henter dataen fra /api/products apiet.
	renderProducts: function(Products) { // vi bruger ikek rigtigt de produkter vi passer ind som parameter, men vi kunen godt
		let data = '';

		// data kommer til at indeholde en masse 'tr' elementer saa tabellen bliver fuldendet. hvert produkt har dets egen tr
		if (this.Products.length > 0) {
			for (i = 0; i < this.Products.length; i++) {
				data += `<tr id="product${this.Products[i].productId}">`;
				data += `<td>${this.Products[i].productId}</td>`;
				data += `<td>${this.Products[i].productName}</td>`;
				data += `<td>${this.Products[i].ingredients.length}</td>`;

				data += `<td><button type="button" onclick="productApp.setForm('addIngredient', ${this.Products[i].productId}, '${this.Products[i].productName}')" 
                                     class="editbtn btn btn-success" aria-label="Edit">&#9998;</button></td>`;

				data += `<td><button type="button" onclick="productApp.setForm('update', ${this.Products[i].productId}, '${this.Products[i].productName}')" 
                                     class="editbtn btn btn-primary" aria-label="Edit">&#9998;</button></td>`;
				data += `<td><button type="button" onclick="productApp.closebtn(this)" class="closebtn btn btn-danger" 
                data-name="${this.Products[i].productName}"
                data-id="${this.Products[i].productId}" aria-label="Close">&times;</button></td>`;
				data += `</tr>`;
			}
		}
		// vi opdaterer count, svarende til maengden af produkter i databsen
		this.Count(this.Products.length);
		// Vi opdaterer htmlen af productEl elementet, hvilket er tbody af produkt tabellen.
		this.ProductEl.html(data);
	},

	<!--RenderInputField PRODUCT-->
	// denne funktion saetter et givet produkts information som vaerdier i input formen
	renderInputFields: function(id) {
		// find produktet ved hjaelp af praedikatet el.productId === id, altsaa det produkt med id = id
		let product = this.Products.filter((el) => el.productId === id)[0];
		// der er kun 2 felter i formen, id og navn
		if (product) {
			$("#productIdInput").val(product.productId);
			$("#productNameInput").val(product.productName);
		}
	},

	// denne funktion goer det samme som renderinputfields, men for addingredients formen. her sker lidt mere
    renderInputFieldsIngredients: function(id){
		//foerst saetter vi formens id til at vaere det id vi faar i funktionen
        $("#productIdInput").val(id);
        // vi fjerner alle elementer i raavaremateriale listen, saa vi kan genskabe den
		$("#rawMatIdInputIngredient").empty();
		// vi genskaber listen ved et givet produks ingredienser, da de skal fjernes fra ingredienslisten
		let ingredient = this.Products.find(x => x.productId == id).ingredients;

		// vi filtrerer de ingredienser vaek som allerede indgaar i produktet
		let populate = this.IngredientsFromDB.filter((x) => {
			return ingredient.find(y => y.rawMatId == x.rawMatID) == undefined;
		})

		// for hver ingrediens i den resulterende liste tilfoejer vi et element i dropdown menuen til rawmat
		populate.map((el) => {
            $("#rawMatIdInputIngredient").append($("<option></option>")
                .attr("value",el.rawMatID)
                .text(`${el.rawMatID} - ${el.rawMatName}`));
        })
	},

	// hvis et produkt allerede har ingredienser tilegnet, bliver de tilfoejet til ingredienstabellen i formen her gennem renderAddIngredient
    renderIngredientsTable: function(product){
		product.ingredients.map((ingredient) => {
			let ingname = this.IngredientsFromDB.find((y) => y.rawMatID == ingredient.rawMatId).rawMatName
			this.renderAddIngredient(ingredient, ingname, product.productId);

		})
    },

	// denne funktion er den der tilfoejer ingrediensen til tabellen naar en bruger tilfoejer ingredienser, samt de eksiterende ingredienser
	renderAddIngredient: function (el, name, pid){
		// Use == because rawmatID likely is string...
		$("#ingredientFormTable").append(`<tr id="ing${name + el.amount + el.tolerance}"><td>${el.rawMatId}</td><td>${name}</td>`+
			`<td>${el.amount}</td><td>${el.tolerance}</td><td><button type="button" onclick="productApp.deleteIngredient(this)" class="closebtn btn btn-danger" 
                data-name="${name}" data-prodid="${pid}"
                data-amount="${el.amount}" data-tolerance="${el.tolerance}" aria-label="Close">&times;</button></td></tr>`)

	},
};