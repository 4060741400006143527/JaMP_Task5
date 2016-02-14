jstat -gccapacity PID

1,2. -Xms128m -Xmx512m -XX:NewRatio=1 -XX:SurvivorRatio=3

3. -Xmx512m -XX:OldSize=128m -XX:SurvivorRatio=3

4. -XX:OldSize=128m -XX:NewSize=384m -XX:SurvivorRatio=3

5. -XX:OldSize=128m -XX:NewSize=640m -XX:SurvivorRatio=3 -Xss1m