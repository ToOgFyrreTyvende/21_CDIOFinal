const utils = {
	createForm: true,
	users: [],
	roles: [],
	Count: function(data) {
		let name = 'user';
		counter = $('#counter');
		if (data) {
			if (data > 1)
				name = 'users';

			counter.html(data + ' ' + name);
		} else
			counter.html('No ' + name);
	},
	FetchAllUsers: function() {
		$.get('/api/users').done((data) => {
			this.users = data.sort((a, b) => (a.userId > b.userId) ? 1 : -1);
			this.renderUsers(data);
		})
	},
	FetchAllRoles: function() {
		$.get('/api/users/roles').done((data) => {
			this.roles = data;
			this.renderRoles(data);
		})
	},
	removeWithId: function(id) {
		$.ajax({
			url: `/api/users/${id}`,
			type: 'DELETE',
			success: () => {
				$(`#user${id}`).remove();
				console.log("Deleted!, fetching now");
				this.FetchAllUsers();
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
			$("#userIdInput").prop('disabled', false);
		} else {
			this.createForm = false;
			this.renderInputFields(id);
			$("#userIdInput").prop('disabled', true);
			$("#modalText").text(`Update ${name}, #${id}`);
			$('#createUserModal').modal();
		}
	},
	createUser: function() {
		let _this = this;
		let editedData = getFormData($('#inputForm'));
		editedData.role = parseInt(editedData.roles[0]);
		delete editedData.roles;
		$.ajax({
			type: "POST",
			url: "/api/users",
			data: JSON.stringify(editedData),
			contentType: "application/json; charset=utf-8",
			success: function(data) {
				$('#createUserModal').modal('toggle');
				document.getElementById("inputForm").reset();
				_this.FetchAllUsers();
			},
			failure: function(errMsg) {
				alert(errMsg);
				console.error(errMsg);
			}
		});
	},
	updateUser: function() {
		let _this = this;
		$("#userIdInput").prop('disabled', false);
		let editedData = getFormData($('#inputForm'));
		editedData.role = parseInt(editedData.roles[0]);
		delete editedData.roles;
		$.ajax({
			type: "PUT",
			url: "/api/users",
			data: JSON.stringify(editedData),
			contentType: "application/json; charset=utf-8",
			success: function(data) {
				$('#createUserModal').modal('toggle');
				document.getElementById("inputForm").reset();
				_this.FetchAllUsers();
			},
			failure: function(errMsg) {
				alert(errMsg);
				console.error(errMsg);
			}
		});
		$("#userIdInput").prop('disabled', true);
	}
};

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


