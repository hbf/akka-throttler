To publish a new version on Github, a project admin needs to:

1. Commit all changes in the master branch and push them to Github (`git commit -a -m "..."` and `git push origin` in the master branch); 
2. `sbt test doc '+ publish'`, which will publish to the local Maven repository;
3. Copy the directory `target/scala*/api` to the `doc` directory of the Github `gh-pages` branch: `rm -fr ../gh-pages/doc/api/; cp -R target/scala-2.9.2/api ../gh-pages/doc/`
4. Regenerate the directory listings for the Maven repo using `./create-directory-listings.sh`;
5. Commit all changes in the `gh-pages` branch and push them to Github (`git commit -a -m "..."` and `git push origin` in the `gh-pages` branch).
