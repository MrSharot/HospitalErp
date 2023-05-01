# HospitalErp

a REST service where users are able to book online video consultations appointment for a doctor. For simplicity let’s keep the expertise of the doctor limited to general physician.

Feature list:
•	User onboarding (registration and login).
•	Doctor onboarding (registration and login).
•	Time slots for doctors. Example: Doctor A will e available from 10am to 12pm. So, time slots will be 10-11 and 11-12.
•	User will be able to book a time slot at a specific date. Once the time slot is booked other users won’t be able to book the same time slot for the same doctor.
•	Doctor should be able to the user information who booked the slot.
Optional feature:

Stack:
•	Spring boot
•	Spring Data JPA
•	MySQL
