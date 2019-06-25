const rendersProdBatch = {
	renderProductBatches: function(ProductBatches) {
		let data = '';
		let productStatusText = ["Not started", "In production", "Done"];


		if (this.ProductBatches.length > 0) {
			for (i = 0; i < this.ProductBatches.length; i++) {

				data += `<tr id="prodbatch${this.ProductBatches[i].prodBatchId}" class="colourstatus${this.ProductBatches[i].status}">`;
                data += `<td>${this.ProductBatches[i].prodBatchId}</td>`;
                data += `<td>${this.ProductBatches[i].name}</td>`;
				data += `<td>${this.ProductBatches[i].prodId}</td>`;
				data += `<td>${productStatusText[this.ProductBatches[i].status]}</td>`;
				//data += `<td>${this.ProductBatches[i].userId}</td>`;
				data += `<td>${this.ProductBatches[i].weighings.length}</td>`;

                data += `<td><button type="button" onclick="prodBatchApp.displayProduct(${this.ProductBatches[i].prodBatchId}, '${this.ProductBatches[i].name}')" 
                                     class="editbtn btn btn-primary" aria-label="Edit">&#128269;</button></td>`;
				data += `<td><button type="button" onclick="prodBatchApp.setForm('update', ${this.ProductBatches[i].prodBatchId}, '${this.ProductBatches[i].name}')" 
                                     class="editbtn btn btn-primary" aria-label="Edit">&#9998;</button></td>`;
				data += `<td><button type="button" onclick="prodBatchApp.closebtn(this)" class="closebtn btn btn-danger" 
                data-name="${this.ProductBatches[i].productBatchName}"
                data-id="${this.ProductBatches[i].prodBatchId}" aria-label="Close">&times;</button></td>`;
				data += `</tr>`;
			}
		}
		console.log(this.ProductBatches.length);
		this.Count(this.ProductBatches.length);
		this.ProductBatchEl.html(data);
	},

	<!--RenderInputField PRODUKTBATCH-->
	renderInputFields: function(id) {
		let productBatch = this.ProductBatches.filter((el) => el.prodBatchId === id)[0];
		if (productBatch) {
			$("#productBatchIdInput").val(productBatch.prodBatchId);
            $("#productIdInputDropDown").val(productBatch.prodId);
            $("#statusInput").val(productBatch.status);
			$("#userIDInput").val(productBatch.userId);
			$("#RMBIdInput").val(productBatch.RMBId);
		}
	},

    renderSelectOptions: function() {
        $("#productIdInputDropDown").empty();
        let populate = this.Products;
        populate.map((el) => {
            $("#productIdInputDropDown").append($("<option></option>")
                .attr("value",el.productId)
                .text(`${el.productId} - ${el.productName}`));
        })
    },

    renderPrintTable: function(prodBatchEl) {
        $("#displayTable").empty();
	    let productEl = this.Products.find(x => x.productId == prodBatchEl.prodId);

	    if(productEl){
	        productEl.ingredients.map((x) =>{
	            let weighing = prodBatchEl.weighings.filter(y => x.rawMatId == y.rawMatId);
	            var alreadyweighed = false;
	            var toWeigh = parseFloat(x.amount);
	            debugger;
	            if(weighing.length > 0){
                    alreadyweighed = true;
                    prodBatchEl.weighings.map(w => toWeigh -= parseFloat(w.netto));
                }

            $("#displayTable").append(`<tr><td>${x.rawMatId}</td><td>${x.name}</td><td>${x.amount}</td>
                                   <td>${x.tolerance}</td>
                                   <td>${alreadyweighed? "yes" : "no"}</td>
                                   </tr>`) // <td>${toWeigh}</td>

            })
        }


    }
};