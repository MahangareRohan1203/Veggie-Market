
// #########################################################################Add Functon ###############################################################################
// ####################################################################################################################################################################
// #####################################################################################################################################################################
function AddFeedback(){
    let vegetableId=document.getElementById("vegetableId").value;
    let rating=document.getElementById("rating").value;
   
    let comments=document.getElementById("comments").value;
    // let feedbackDateTime=document.getElementById("Date").value;
    

    
    fetch(`http://localhost:8999/vegiee/user/feedback/add/${vegetableId}/${customerId}`,{
    
        method: "POST",
        headers: {
            "content-type": "application/json"
        },
        body: JSON.stringify({
            "vegetableId": vegetableId,
            "rating": rating,
            "comments": comments,
          
            "feedbackDateTime": new Date().toJSON(),
    
        })
    }).then(response => {
        document.getElementById("p").innerHTML = "Feedback added Successfully";
        var modal = document.getElementById("myModal");
        modal.style.display = "block"; 
        
    })
    
    
    }





// ######################################################################## Upadate function ##############################################################################
// ####################################################################################################################################################################
// #####################################################################################################################################################################



    function UpdateFeedback(){
        let  feedbackId=document.getElementById("feedbackId").value;
        let vegetableId=document.getElementById("vegetableId").value;
        let rating=document.getElementById("rating").value;
   
         let comments=document.getElementById("comments").value;
       
        
        fetch(`http://localhost:8999/vegiee/user/feedback/update`,{
        
            method: "PUT",
            headers: {
                "content-type": "application/json"
            },
            body: JSON.stringify({
                "feedbackId":feedbackId,
                "vegetableId": vegetableId,
               "rating": rating,
                "comments": comments,
          "feedbackDateTime": new Date().toJSON(),
        
            })
        }).then(response => {
            
               
            document.getElementById("p").innerHTML = "Feedback Updated Successfully";
            var modal = document.getElementById("myModal");
            modal.style.display = "block"; 
                
           
            
        })
        
        
        }

// // #########################################################################View Function ##############################################################################
// #
// // ####################################################################################################################################################################
// // #####################################################################################################################################################################



        function f1(){

            const viewfeedback = document.getElementById('viewfeedback');
            
            fetch("http://localhost:8999/vegiee/feedback/all").then(response => {
              
                    response.json().then(data => {
                       
                       data.forEach(Feedback =>{
            
                        const row = document.createElement('tr');
              row.innerHTML = `
                <td>${Feedback.feedBackId}</td> 
                <td>${Feedback.vegetableId}</td>
                <td>${Feedback.rating}</td>
                
                <td>${Feedback.comments}</td>
                <td>${Feedback.customer.name}</td>
              
               
              `;
              viewfeedback.appendChild(row);
            
             })
            })
               
            } ) 
                }


// #########################################################################Deletebutton###############################################################################
// ####################################################################################################################################################################
// #####################################################################################################################################################################

                function deleteRecord(){
                    document.getElementById("p").innerHTML = "Are You Sure ?";
                    var modal = document.getElementById("myModal");
                    modal.style.display = "block"; 

                    
                    let  id=document.getElementById("feedBackId2").value
                
                    fetch(`http://localhost:8999/vegiee/user/feedback/delete/${id}`, {
                    
                    method: "DELETE"
                    
                    }).then(response => {
                   
                        
                        document.getElementById("p").innerHTML = "Feedback Deleted Successfully";
                        var modal = document.getElementById("myModal");
                        modal.style.display = "block"; 
                            
                       
                    
                    })
                    
                    
                    }
                    




// #########################################################################alertbutton###############################################################################
// ####################################################################################################################################################################
// #####################################################################################################################################################################
               
var modal = document.getElementById("myModal");


var span = document.getElementsByClassName("close")[0];


span.onclick = function() {
    modal.style.display = "none";
}





    
             
               
               
              