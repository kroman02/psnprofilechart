# Roundtable Hold - PSN Profiles Chart

This is a Java & Spring-Boot based web application that retrieves PSN profiles information and displays the users in a table, ranking them by either trophies, types of trophies or level.  
Profile information is gathered from [PSNProfiles](https://psnprofiles.com/).

The application exposes API endpoints to interact with the underlying database that updates the table via MVC. 

**Visit the application at: http://roundtablehold.com**

## API endpoints

#### Add a profile | request type: POST
If the user exists, the profile is added to the table. If the profile is private, a [PRIVATE] tag is appended to the username.

roundtablehold.com/api/v1/profiles/add?username=USERNAME

---

#### Get profile | request type: GET
If the user exists, the profile object is returned without being added to the table.

roundtablehold.com/api/v1/profiles/getPSNProfile?username=USERNAME

---

#### Get all profiles | request type: GET
Returns an array containing all profiles from the table.

roundtablehold.com/api/v1/profiles/getall

---

#### Delete profile | request type: DELETE
If the profile exists within the table, it deletes it.

roundtablehold.com/api/v1/profiles/remove?username=USERNAME



