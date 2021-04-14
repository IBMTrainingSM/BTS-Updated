function loadBug(){
	const promise = fetch('/bug');
		promise.then(function(response){
			return response.json();
		})
		.then(function(bugs){
			const selectName = document.getElementById('bugName');
			for(let i=0;i<bugs.length;i++){
				const currentBug = bugs[i];
				const option = document.createElement('option');
				option.setAttribute("value",currentBug.id);
				option.append(currentBug.name);
				selectName.appendChild(option);
			}
        })
}
function getBug(){
		let bugId=document.getElementById('bugName').value;
		if (!bugSearchForm.checkValidity()) {
				alert('Bug id is invalid');
				return;
			}
		bugId = bugId.trim();
		if (bugId) {
			const promise = fetch('/bug/' + bugId);
			promise.then(function(response) {
				return response.json();
			}).then(function(bug) {
				if (bug) {
					let etaDate = bug.etaDate;
					if(etaDate){
						let finalEtaDate = etaDate.split('T')[0];
						document.getElementById('etaDate').value = finalEtaDate;
					}
					document.getElementById('name').value = bug.name;
					document.getElementById('priority').value = bug.priority;
					document.getElementById('type').value = bug.type;
					document.getElementById('severity').value = bug.severity;
					document.getElementById('module').value = bug.module;
					document.getElementById('buildVersion').value = bug.buildVersion;
					document.getElementById('description').value = bug.description;
					document.getElementById('projectId').value = bug.projectId;
					document.getElementById('developerId').value = bug.developerId;
					document.getElementById('testerId').value = bug.testerId;
					document.getElementById('submittedOn').value = bug.submittedOn;
					document.getElementById('synopsis').value = bug.synopsis;
					document.getElementById('product').value = bug.product;
					document.getElementById('status').value = bug.status;
				} else {
					alert("Invalid Bug Id");
				}
			});
		} else {
			alert("Invalid Bug Id");
		}

}
function updateBug(){
	function success(response) {
			if (!response.ok) {
				alert('error happned');
				return;
			}
			alert("Bug is Updated");
			return response.json();
		}
		const bugForm = document.getElementById('bugCreateForm');

		if (!bugForm.checkValidity()) {
			alert('form is invalid');
			return;
		}
		let description = document.getElementById('description').value;
		if (100 < description.length || description.length < 10) {
			alert("Description should be between 10 and 100 characters");
			return;
		}

		let synopsis = document.getElementById('synopsis').value;
		if (50 < synopsis.length || synopsis.length < 10) {
			console.log("synopsis length=" + synopsis.length)
			alert("Synopsis should be between 10 and 50 characters");
			return;
		}
		const bugId=document.getElementById('bugName').value;
		const promise = fetch('/bug/'+bugId, {
			method : 'PUT',
			headers : {
				'Content-Type' : 'application/json'
			},
			body : JSON.stringify({
				name : document.getElementById('name').value,
				priority : document.getElementById('priority').value,
				type : document.getElementById('type').value,
				status : document.getElementById('status').value,
				module : document.getElementById('module').value,
				buildVersion : document.getElementById('buildVersion').value,
				description : document.getElementById('description').value,
				severity : document.getElementById('severity').value,
				projectId : document.getElementById('projectId').value,
				developerId : document.getElementById('developerId').value,
				testerId : document.getElementById('testerId').value,
				synopsis : document.getElementById('synopsis').value,
				product : document.getElementById('product').value,
				etaDate : document.getElementById('etaDate').value,
				submittedOn : document.getElementById('submittedOn').value
			})
		});
		promise.then(success);

}