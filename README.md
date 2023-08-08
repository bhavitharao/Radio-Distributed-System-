# Radio-Distributed-System
A distributed program called the Music Radio System enables publishers to upload music to a central server and users to download songs from the server. To enable remote communication between the clients and the server, Java Remote Method Invocation (RMI) technology is used in its implementation. Only authenticated users will be able to access the music collection thanks to the system's secure login support for both publishers and consumers.

1. Compile all the Java files using the javac command as follows: javac RadioServer.java RadioServerImpl.java RadioServerMain.java PublisherClient.java ConsumerClient.java Song.java
2. In another terminal, run the RMI registry invocation: rmiregistry
3. Run the RadioServerMain class using the Java command to get the server running
4. Open another terminal and execute the PublisherClient class using the Java command. After giving the appropriate username and password, we can log in to the account and add songs.
5. In another terminal, run the ConsumerClient class using the Java command. Provide the necessary credentials to log in, after successful login consume the available songs
6. All the songs produced can be seen at the RadioServerMain terminal
