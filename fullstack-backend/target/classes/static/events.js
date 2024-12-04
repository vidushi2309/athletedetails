// Elements
const eventList = document.getElementById("event-list");
const registrationModal = document.getElementById("registration-modal");
const closeModal = document.getElementById("close-modal");
const registrationForm = document.getElementById("registration-form");
const emailInput = document.getElementById("email");
const phoneInput = document.getElementById("phone");
const ageInput = document.getElementById("age");

// Fetch events from the database and display them
async function loadEvents() {
  try {
    const response = await fetch('http://localhost:8080/events'); // Replace with your API endpoint
    if (!response.ok) {
      throw new Error("Failed to fetch events");
    }
    const events = await response.json();

    // Display events
    events.forEach(event => {
      const eventItem = document.createElement("div");
      eventItem.className = "event-item";
      eventItem.innerHTML = `
        <div class="event-image-container">
          <img src="${event.image || 'default-image.jpg'}" alt="${event.eventTitle}" class="event-image">
        </div>
        <h3>${event.eventTitle}</h3>
        <p><strong>Date:</strong> ${new Date(event.eventDate).toLocaleDateString()}</p>
        <p><strong>Location:</strong> ${event.location}</p>
        <button class="register-btn" data-event-id="${event.eventId}">Register</button>
      `;
      eventList.appendChild(eventItem);
    });
  } catch (error) {
    console.error("Error loading events:", error);
    eventList.innerHTML = "<p>Failed to load events. Please try again later.</p>";
  }
}

// Open registration modal on event button click
eventList.addEventListener("click", function (e) {
  if (e.target.classList.contains("register-btn")) {
    const eventId = e.target.getAttribute("data-event-id");
    // You can optionally fetch event details here if needed
    registrationModal.style.display = "flex";
  }
});

// Close modal
closeModal.addEventListener("click", function () {
  registrationModal.style.display = "none";
});

// Handle form submission
registrationForm.addEventListener("submit", function (e) {
  e.preventDefault();

  const email = emailInput.value.trim();
  const phone = phoneInput.value.trim();
  const age = ageInput.value.trim();

  if (email && phone && age) {
    alert(`Successfully registered for the event! \nEmail: ${email} \nPhone: ${phone} \nAge: ${age}`);
    registrationModal.style.display = "none";
    // Clear the form fields
    emailInput.value = "";
    phoneInput.value = "";
    ageInput.value = "";
  } else {
    alert("Please fill out all fields.");
  }
});

// Load events on page load
window.onload = loadEvents;
