const rendersProduct = {
    renderRecepts: function (Recepts) {
        let data = '';

        if (this.Products.length > 0) {
            for (i = 0; i < this.Products.length; i++) {
                data += `<tr id="product${this.Products[i].receptId}">`;
                data += `<td>${this.Products[i].receptId}</td>`;
                data += `<td>${this.Products[i].receptName}</td>`;
                data += `<td>${this.Products[i].rawMatId}</td>`;
                data += `<td>${this.Products[i].nonNetto}</td>`;
                data += `<td>${this.Products[i].tolerance}</td>`;

                data += `<td><button type="button" onclick="productApp.setForm('update', ${this.Products[i].receptId}, '${this.Products[i].receptName}')" 
                                     class="editbtn btn btn-primary" aria-label="Edit">&#9998;</button></td>`;
                data += `<td><button type="button" onclick="productApp.closebtn(this)" class="closebtn btn btn-danger" 
                data-name="${this.Products[i].receptName}"
                data-id="${this.Products[i].receptId}" aria-label="Close">&times;</button></td>`;
                data += `</tr>`;
            }
        }
        console.log(this.Products.length)
        this.Count(this.Products.length);
        this.ReceptEL.html(data);
    },

    <!--RenderInputField PRODUCT-->
    renderInputFields: function(id){
        recept = this.Products.filter((el) => el.receptId === id)[0]
        if(user){
            $("#receptIdInput").val(recept.receptId);
            $("#receptNameInput").val(recept.receptName);
            $("#rawMatInput").val(recept.rawMatId);
            $("#nonNettoInput").val(recept.nonNetto);
            $("#toleranceInput").val(recept.tolerance);
        }
    }
}