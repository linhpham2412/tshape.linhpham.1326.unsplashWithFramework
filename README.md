# tshape.linhpham.1326.unsplashWithFramework

Test Application: https://unsplash.com

API documentation: https://unsplash.com/documentation

how to get access OAUTH token
1. go to https://unsplash.com/oauth/applications
2. create your app
3. open your app
4. save access key and secrete key
5. input urn:ietf:wg:oauth:2.0:oob into redirect uri
6. click authorize to get code
7. send POST request https://unsplash.com/oauth/token?client_id="AccessKey"&client_secret="ScreteKey"&redirect_uri=urn:ietf:wg:oauth:2.0:oob&code="CodeGenerated"&grant_type=authorization_code
8. save the access_token value as OAUTH token for api (only 1 success return for each code)

Test scenarios:

Scenario 1: Follow a photographer successfully

* Given I log in with account "<account_name>"

* And I click the first photo on home page

* When I hover on icon user at the top left corner

* And I click the Follow button

* Then I observe button text turn into Following

Scenario 2: Update the username URL in the Profile page

* Given I log in with account "<account_name>"

* And I go to the Profile page

* When I click Edit tags link

* And I edit the username field

And I click the Update Account button  And I go to https://unsplash.com/@<new_username>

* Then I observe that it will take me to the Profile page

* And My full name is displayed as <your_fullname>

Scenario 3: List of liked photos

* Given I log in with account "<account_name>"

And I like 3 random photos  When I go to https://unsplash.com/@user_name/likes

* Then I see the number of likes is 3

* And 3 photos appear in Likes section

Note: use API for unliking photos

Scenario 4: Remove photos from the collection successfully

* Given I log in with account "<account_name>"

* And I create a private collection

* And I add 2 random photos to the newly created collection

* And I remove 1 photo from the newly created collection

When I go to https://unsplash.com/collections/collection_id*

* Then I notice that the photo has been removed successfully from the collection

* And there is only 1 remaining photo in the collection

Note: use API for deleting collection

Scenario 5: Download photo successfully

* Given I log in with account "<account_name>"

* When I open a random photo

* And I download this photo

* Then I notice that the image is downloadable and the correct image has been saved