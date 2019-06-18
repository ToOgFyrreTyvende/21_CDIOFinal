const utilsProdBatch = {
	createForm: true,
	ProductBatches: [],
	Count: function(data) {
		let name = 'Product Batch';
		counter = $('#counterPB');
		if (data) {
			if (data > 1)
				name = 'Product Batches';

			counter.html(data + ' ' + name);
		} else
			counter.html('No ' + name);
	},
	FetchAllProductBatches: function() {
		$.get('/api/productBatches').done((data) => {
			this.ProductBatches = data.sort((a, b) => (a.prodBatchId > b.prodBatchId) ? 1 : -1);
			this.renderProductBatches(data);
		})
	},
	removeWithId: function(id) {
		$.ajax({
			url: `/api/productBatches/${id}`,
			type: 'DELETE',
			dataType: 'text',
			success: () => {
				$(`#ProductBatch${id}`).remove();
				console.log("Deleted!, fetching now");
				this.FetchAllProductBatches();
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
            $("#productBatchIdInput").prop('disabled', false);
			document.getElementById("inputFormProductBatch").reset();
			$("#modalText").text("Create");
		} else {
			this.createForm = false;
			this.renderInputFields(id);
			$("#productBatchIdInput").prop('disabled', true);
			$("#modalText").text(`Update ${name}, #${id}`);
			$('#createProductBatchModal').modal();
		}
	},
	createProductBatch: function() {
		let _this = this;
		$.ajax({
			type: "POST",
			url: "/api/productBatches",
			data: JSON.stringify(getFormData($('#inputFormProductBatch'))),
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			success: function(data) {
				$('#createProductBatchModal').modal('toggle');
                $("#inputFormProductBatch")[0].reset();
                _this.FetchAllProductBatches();
			},
			error: function(errMsg) {
				alert("Error, id might already be in use. Put in 0 for auto generated.");
				console.error(errMsg);
			}
		});
	},
	updateProductBatch: function() {
		let _this = this;
		$("#productBatchIdInput").prop('disabled', false);
		$.ajax({
			type: "PUT",
			url: "/api/productBatches",
			data: JSON.stringify(getFormData($('#inputFormProductBatch'))),
			contentType: "application/json; charset=utf-8",
			success: function(data) {
				$('#createProductBatchModal').modal('toggle');
                $("#inputFormProductBatch")[0].reset();
                _this.FetchAllProductBatches();
            },
			failure: function(errMsg) {
				alert(errMsg);
				console.error(errMsg);
			}
		});
		$("#productBatchIdInput").prop('disabled', true);
	}
};


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


