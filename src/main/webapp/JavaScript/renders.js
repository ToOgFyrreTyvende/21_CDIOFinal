const renders = {
    renderUsers: function (users) {
        let data = '';
        
        if (this.users.length > 0) {
            for (i = 0; i < this.users.length; i++) {
                data += `<tr id="user${this.users[i].userId}">`;
                data += `<td>${this.users[i].userId}</td>`;
                data += `<td>${this.users[i].userName}</td>`;
                data += `<td>${this.users[i].ini}</td>`;
                data += `<td>${this.users[i].roles.reduce((el, acc) => acc + ', ' + el)}</td>`;
                data += `<td>${this.users[i].cpr}</td>`;
                data += `<td><button type="button" onclick="app.setForm('update', ${this.users[i].userId}, '${this.users[i].userName}')" 
                                     class="editbtn btn btn-primary" aria-label="Edit">&#9998;</button></td>`;
                data += `<td><button type="button" onclick="app.closebtn(this)" class="closebtn btn btn-danger" 
                data-name="${this.users[i].userName}"
                data-id="${this.users[i].userId}" aria-label="Close">&times;</button></td>`;
                data += `</tr>`;
            }
        }
        console.log(this.users.length)
        this.Count(this.users.length);
        this.userEl.html(data);
    },
    renderRoles: function (roles) {
        let data = '';
        
        if (roles.length > 0) {
            for (i = 0; i < roles.length; i++) {
                data += `<option value="${roles[i].name}">${roles[i].name}</option>`;
            }
        }
        this.roleEl.html(data);
    },


    <!--RenderInputField user-->
    renderInputFields: function(id){
        user = this.users.filter((el) => el.userId === id)[0]
        if(user){
            $("#userIdInput").val(user.userId);
            $("#userNameInput").val(user.userName);
            $("#iniInput").val(user.ini);
            $("#cprInput").val(user.cpr);
            $("#passwordInput").val(user.password);
            $(`#roles option`).prop('selected', false);
            user.roles.map((x) => $(`#roles option[value='${x}']`).prop('selected', true))
        }
    },

    <!--RenderInputField RAW MAT-->
    renderInputFields: function(id){
        RawMaterial = this.users.filter((el) => el.userId === id)[0]
        if(RawMaterial){
            $("#rawMatInput").val(RawMaterial.rawMatId);
            $("#rawMatNameInput").val(RawMaterial.rawMatName);
            $("#supplierInput").val(RawMaterial.supplier);
        }
    },

    <!--RenderInputField RAW MAT BATCH-->
    renderInputFields: function(id){
        user = this.users.filter((el) => el.userId === id)[0]
        if(user){
            $("#RMBIdInput").val(user.RMBId);
            $("#rawMatInput").val(user.rawMatId);
            $("#amountInput").val(user.amount);
        }
    },

    <!--RenderInputField RECEPT-->
    renderInputFields: function(id){
        user = this.users.filter((el) => el.userId === id)[0]
        if(user){
            $("#receptIdInput").val(user.receptId);
            $("#receptNameInput").val(user.receptName);
            $("#rawMatInput").val(user.rawMatId);
            $("#nonNettoInput").val(user.nonNetto);
            $("#toleranceInput").val(user.tolerance);
        }
    },

    <!--RenderInputField PRODUKTBATCH-->
    renderInputFields: function(id){
        user = this.users.filter((el) => el.userId === id)[0]
        if(user){
            $("#productBatchIdInput").val(user.productBatchId);
            $("#receptIdInput").val(user.receptId);
            $("#statusInput").val(user.status);
            $("#userIDInput").val(user.userId);
            $("#RMBIdInput").val(user.RMBId);
            $("#taraInput").val(user.tara);
            $("#nettoInput").val(user.netto);
        }
    }
}