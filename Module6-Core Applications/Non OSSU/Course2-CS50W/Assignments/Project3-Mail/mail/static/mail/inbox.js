
document.addEventListener('DOMContentLoaded', function() {

  // Use buttons to toggle between views
  document.querySelector('#inbox').addEventListener('click', () => load_mailbox('inbox'));
  document.querySelector('#sent').addEventListener('click', () => load_mailbox('sent'));
  document.querySelector('#archived').addEventListener('click', () => load_mailbox('archive'));
  document.querySelector('#compose').addEventListener('click', compose_email);
  //Defining the event listener to send the mail 
  document.querySelector('#compose-form').addEventListener('submit', send_email); 
  //Defining the event listener to archive an email
  
  document.querySelector("#render-archive").addEventListener('click', function() {
    const emailId = document.querySelector("#render-archive").dataset.emailId
    archive_email(emailId)
  })
  //Defining th eevent listener for the reply button
  document.querySelector("#render-reply").addEventListener('click', function() {
    const emailId = document.querySelector("#render-reply").dataset.emailId 
    console.log(`the email id for the replied email is ${emailId}`)
    compose_email_reply(emailId)
  })
  // By default, load the inbox
  load_mailbox('inbox');
});


///////////////////////////////////////////////////////////////////////////
//Defining the call back functions

//Function to render Compose Mail page for reply
function compose_email_reply(id) {
  // Show compose view and hide other views
  document.querySelector('#render-email-view').style.display = 'none';
  document.querySelector('#emails-view').style.display = 'none';
  document.querySelector('#compose-view').style.display = 'block';
  //Now send a fetch request to get the email content
  fetch(`/emails/${id}`)
  .then(response => response.json())
  .then(email => {
    document.querySelector('#compose-recipients').value = email.sender;
    const subjectNew = email.subject.slice(0,3) === "Re:" ? email.subject : `Re: ${email.subject}`;
    document.querySelector('#compose-subject').value = subjectNew;
    var bodyNew = `On ${email.timestamp} ${email.sender} wrote: ${email.body}`
    document.querySelector('#compose-body').value = bodyNew;
  })

}



//Function to render Compose Mail page
function compose_email() {

  // Show compose view and hide other views
  document.querySelector('#render-email-view').style.display = 'none';
  document.querySelector('#emails-view').style.display = 'none';
  document.querySelector('#compose-view').style.display = 'block';

  // Clear out composition fields
  document.querySelector('#compose-recipients').value = '';
  document.querySelector('#compose-subject').value = '';
  document.querySelector('#compose-body').value = '';

  

}

//Function to render mailbox pages for sent, archived, inbox
function load_mailbox(mailbox) {
  
  // Show the mailbox and hide other views
  document.querySelector('#emails-view').style.display = 'block';
  document.querySelector('#render-email-view').style.display = 'none';
  document.querySelector('#compose-view').style.display = 'none';

  // Show the mailbox name
  document.querySelector('#emails-view').innerHTML = `<h3>${mailbox.charAt(0).toUpperCase() + mailbox.slice(1)}</h3>`;

  //Sending the fetch request - the views.py code sends back all the emails in one go, no functionality ofpicking and chosing which emails we want 

  fetch(`/emails/${mailbox}`)
  .then(response => response.json())
  .then(emails => {
    console.log(emails) //CHecking if we are receiving the mails correctly
    console.log("now printing each mail separately")
    for (var i = 0; i < emails.length; i ++){
      add_email(emails[i]) //Calling the add_email function for each email
    }
  })
  .catch(error => {
    console.log(`Error: ${error}`)
  })

}

//Function to add email based on email data passed through
function add_email(email) {
  const element = document.createElement('div')
  if (email.read == true) {element.className = "readEmail"} else {element.className = "unreadEmail"}
  element.dataset.emailID = email.id; //this will help us capture the id
  element.innerHTML = `Sent By: ${email.sender}<div class="subject"> Subject: ${email.subject} </div> Sent on: ${email.timestamp}`
  document.querySelector("#emails-view").append(element)
  console.log(element.dataset.emailID)
  element.addEventListener('click', function() {
    console.log("Just checking something")
    console.log(element.className)
    show_email(element.dataset.emailID)
  })
}


//Functino to fetch the content of individual email and render it on screen

function show_email(id) {
  fetch(`/emails/${id}`)
  .then(response => response.json())
  .then(email => {
    console.log(email)
    console.log("Fetching of email was succesful")
    //Post fetching the email, let's clear out the screen and render the render-email-view
    document.querySelector('#render-email-view').style.display = 'block';
    document.querySelector('#emails-view').style.display = 'none';
    document.querySelector('#compose-view').style.display = 'none';
    document.querySelector("#render-from").innerHTML = `<strong>From:</strong> ${email.sender}`
    document.querySelector("#render-to").innerHTML = `<strong>To:</strong> ${email.recipients}`
    document.querySelector("#render-subject").innerHTML = `<strong>Subject:</strong> ${email.subject}`
    document.querySelector("#render-timestamp").innerHTML = `<strong>Timestamp:</strong> ${email.timestamp}`
    document.querySelector("#render-body").innerHTML = `${email.body}`
    // SET INITIAL BUTTON STATE BASED ON EMAIL.ARCHIVED ← ADD THIS
    const archiveButton = document.querySelector("#render-archive");
    archiveButton.dataset.emailId = id; //this is done so that event listener in the DOM loaded for archive can have access to id of the mail
    const replyButton = document.querySelector("#render-reply")
    replyButton.dataset.emailId = id; // this will help us in getting the right email for the reply event listener at the top
    if (email.archived) {
      archiveButton.value = "Unarchive";
      archiveButton.innerHTML = "Unarchive";
    } else {
      archiveButton.value = "Archive";
      archiveButton.innerHTML = "Archive";
    }
    
    //Now changing the property of the mail from read to unread both on frontend and backend when clicked
    //First backend
    fetch(`/emails/${id}`, {
      method: 'PUT',
      body: JSON.stringify({
        read: true
      })
    })
    console.log("Backend succesfully updated for read")
    //Frontend updated in the add_email event listener itself
    //Event listener for archive is added when DOM is loaded
  })
  .catch(error => {
    console.log(`Error: ${error}`)
  })

}
//Function to archive email
function archive_email(id) {
  const element = document.querySelector("#render-archive")  
  if (element.value === "Archive") {
      fetch(`/emails/${id}`, {
      method:'PUT',
      body: JSON.stringify({
        archived:true
      })
    })
    .then(()=>{
      load_mailbox("inbox")
      element.value = "Unarchive"
      element.innerHTML = "Unarchive"
    })

    }
    else {
      fetch(`/emails/${id}`, {
      method:'PUT',
      body: JSON.stringify({
        archived:false
      })
    })
    .then(()=>{
      load_mailbox("inbox")
      element.value = "Archive"
      element.innerHTML = "Archive"
    })
    }
}


//Function to actually send an email from the compose page 
function send_email(event) {
  event.preventDefault()
  const recipients = document.querySelector("#compose-recipients").value
  const subject = document.querySelector("#compose-subject").value
  const body = document.querySelector("#compose-body").value
  //Defining the API POST method to send the mail
  fetch('/emails', {
    method: 'POST',
    body: JSON.stringify({
        recipients: recipients,
        subject: subject,
        body: body
    })
  })
  .then(response => response.json())
  .then(result => {
      // Print result
      console.log(result);
      console.log("Email succesfully sent")
      //After sending the mail, let'sgo back to the sent page
      load_mailbox('sent')    
      document.querySelector('#compose-form').reset();
      
  })
  .catch(console.error);

  
}