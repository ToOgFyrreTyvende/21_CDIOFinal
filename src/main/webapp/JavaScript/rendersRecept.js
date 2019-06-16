const renders = {
    renderRecepts: function (Recepts) {
        let data = '';

        if (this.Recepts.length > 0) {
            for (i = 0; i < this.Recepts.length; i++) {
                data += `<tr id="user${this.Recepts[i].receptId}">`;
                data += `<td>${this.Recepts[i].receptId}</td>`;
                data += `<td>${this.Recepts[i].receptName}</td>`;
                data += `<td>${this.Recepts[i].rawMatId}</td>`;
                data += `<td>${this.Recepts[i].nonNetto}</td>`;
                data += `<td>${this.Recepts[i].tolerance}</td>`;

                data += `<td><button type="button" onclick="app.setForm('update', ${this.Recepts[i].receptId}, '${this.Recepts[i].receptName}')" 
                                     class="editbtn btn btn-primary" aria-label="Edit">&#9998;</button></td>`;
                data += `<td><button type="button" onclick="app.closebtn(this)" class="closebtn btn btn-danger" 
                data-name="${this.Recepts[i].receptName}"
                data-id="${this.Recepts[i].receptId}" aria-label="Close">&times;</button></td>`;
                data += `</tr>`;
            }
        }
        console.log(this.Recepts.length)
        this.Count(this.Recepts.length);
        this.ReceptEL.html(data);
    },

    <!--RenderInputField RECEPT-->
    renderInputFields: function(id){
        recept = this.Recepts.filter((el) => el.receptId === id)[0]
        if(user){
            $("#receptIdInput").val(recept.receptId);
            $("#receptNameInput").val(recept.receptName);
            $("#rawMatInput").val(recept.rawMatId);
            $("#nonNettoInput").val(recept.nonNetto);
            $("#toleranceInput").val(recept.tolerance);
        }
    }
}