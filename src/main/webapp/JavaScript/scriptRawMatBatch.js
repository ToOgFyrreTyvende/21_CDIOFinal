const utilsRawBatch = {
	createForm: true,
	RawMaterialBatches: [],
	RawMaterials: [],
	Count: function(data) {
		let name = 'Raw Material Batch';
		counter = $('#counterRMB');
		if (data) {
			if (data > 1)
				name = 'Raw Material Batches';

			counter.html(data + ' ' + name);
		} else
			counter.html('No ' + name);
	},
	FetchAllRawMaterialBatches: function() {
		$.get('/api/rawMatBatches').done((data) => {
			this.RawMaterialBatches = data.sort((a, b) => (a.RMBId > b.RMBId) ? 1 : -1);
			this.renderRawMaterialBatches(data);
		});

        $.get('/api/rawMat').done((data) => {
            this.RawMaterials = data.sort((a, b) => (a.rawMatID > b.rawMatID) ? 1 : -1);
        });
	},

	removeWithId: function(id) {
		$.ajax({
			url: `/api/rawMatBatches/${id}`,
			type: 'DELETE',
			dataType: 'text',
			success: () => {
				$(`#RawMaterialBatch${id}`).remove();
				console.log("Deleted!, fetching now");
				this.FetchAllRawMaterialBatches();
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
        this.renderSelectOptions();
		if (status === "create" && id === 0) {
			this.createForm = true;
			document.getElementById("inputFormRawMatBatch").reset();
			$("#modalText").text("Create");
			$("#RMBIdInput").prop('disabled', false);
		} else {
			this.createForm = false;
			this.renderInputFields(id);
			$("#RMBIdInput").prop('disabled', true);
			$("#modalText").text(`Update ${name}, #${id}`);
			$('#createRawMatBatchModal').modal();
		}
	},
	createRawMaterialBatch: function() {
		let _this = this;
		$.ajax({
			type: "POST",
			url: "/api/rawMatBatches",
			data: JSON.stringify(getFormData($('#inputFormRawMatBatch'))),
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			success: function(data) {
				$('#createRawMatBatchModal').modal('toggle');
				document.getElementById("inputFormRawMatBatch").reset();
				_this.FetchAllRawMaterialBatches();
			},
            error: function(errMsg) {
                alert("Error, id might already be in use. Put in 0 for auto generated.");
				console.error(errMsg);
			}
		});
	},
	updateRawMaterialBatch: function() {
		let _this = this;
		$("#RMBIdInput").prop('disabled', false);
		$.ajax({
			type: "PUT",
			url: "/api/rawMatBatches",
			data: JSON.stringify(getFormData($('#inputFormRawMatBatch'))),
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			success: function(data) {
				$('#createRawMatBatchModal').modal('toggle');
				document.getElementById("inputFormRawMatBatch").reset();
				_this.FetchAllRawMaterialBatches();
			},
			failure: function(errMsg) {
				alert(errMsg);
				console.error(errMsg);
			}
		});
		$("#RMBIdInput").prop('disabled', true);
	}
};


/* Fix this */
function getFormData($form) {
	var unindexed_array = $form.serializeArray();
	var indexed_array = {roles: []};

	$.map(unindexed_array, function(n, i) {
		if (n['name'] == "roles") {
			indexed_array[n['name']].push(n['value']);
		} else {
			indexed_array[n['name']] = n['value'];
		}
	});
	return indexed_array;
}


