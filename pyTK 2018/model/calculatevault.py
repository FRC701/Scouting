import sqlite3

from team import*

conn = sqlite3.connect('C:\\Workspace_2018\\pyTK 2018\\scouting.db')
c = conn.cursor()

#indexes for Vault Table
vMatchNum = 1
vAlliance = 2
vForceTime = 3
vForceCubes = 4
vLevTime = 5
vLevCubes = 6
vBoostTime = 7
vBoostCubes = 8
vFceCubesAtTime = 9
vLevCubesAtTime = 10
vBstCubesAtTime = 11

#indexes for Stats Table
sMatchNum = 1
sTeamNum = 2
sMatchPos = 3
sHumanPlayer = 20

def sort_vault():
    global c
    global vMatchNum
    global vAlliance
    global sMatchNum
    global sTeamNum
    global sMatchPos
    global sHumanPlayer

    c.execute('SELECT MatchNumber FROM StatsAll')
    matchNums = c.fetchall()
    lastmatch = 0
    #get the number of matches
    for i in matchNums:
            if i[0] > lastmatch:
                    lastmatch = i[0]

    #process data for each match
    count = 1
    while count < lastmatch+1:
            c.execute('SELECT * FROM VaultAll WHERE MatchNumber = ' + str(count))
            vaults = []
            rowVault = c.fetchone()
            #get all vault info for the current match 
            while rowVault is not None:
                #print (rowVault)
                vaults.append(rowVault)
                rowVault = c.fetchone()
            
            c.execute("""SELECT MatchNumber, MatchPosition, TeamNumber 
                            From StatsAll
                            WHERE MatchNumber = """ + str(count))
                            
            teamPos = c.fetchall()
            
            redTeams = []
            blueTeams = []
            allTeams = []
            for t in teamPos:
                if 3 >= t[1] and t[1] >= 1:
                    redTeams.append(t[2])
                else:
                    blueTeams.append(t[2])

                allTeams.append(t[2])
            
            redTeams = list(set(redTeams))
            blueTeams = list(set(blueTeams))
            allTeams = list(set(allTeams))

            numCubesRed = get_num_cubes(vaults, "Red")
            numCubesBlue = get_num_cubes(vaults, "Blue")

            for sTeam in Team.team_list:
                for sT in redTeams:
                    if sTeam.number == sT:
                        sTeam.Scores.numCubesInVault.append(numCubesRed)

            for sTeam in Team.team_list:
                for sT in blueTeams:
                    if sTeam.number == sT:
                        sTeam.Scores.numCubesInVault.append(numCubesBlue)
			
            c.execute("""SELECT HumanPlayer, TeamNumber
                                    FROM StatsAll
                                    WHERE MatchNumber = """ + str(count))
            sRow = c.fetchall()
            redVaultTeam = 0
            blueVaultTeam = 0
            
            for t in redTeams:
                for r in sRow:
                    if t == r[1] and r[0] == 1:
                        redVaultTeam = r[1]
            for t in blueTeams:
                for r in sRow:
                    if t == r[1] and r[0] == 1:
                        blueVaultTeam = r[1]

            redTimes = []
            redCubesAtTime = []
            blueTimes = []
            blueCubesAtTime = []
            
            redTimes = get_power_up_times(vaults, "Red") if len(get_power_up_times(vaults, "Red")) else [0, 0, 0]
            redCubesAtTime = get_num_cubes_at_time(vaults, "Red") if len(get_num_cubes_at_time(vaults, "Red")) else [0, 0, 0]
            blueTimes = get_power_up_times(vaults, "Blue") if len(get_power_up_times(vaults, "Blue")) else [0, 0, 0]
            blueCubesAtTime = get_num_cubes_at_time(vaults, "Blue") if len(get_num_cubes_at_time(vaults, "Blue")) else [0, 0, 0]

            #print("Red Vault opperated by " + str(redVaultTeam) + " for match " +str(count))
            for sTeam in Team.team_list:
                if sTeam.number == redVaultTeam:
                    sTeam.Scores.activeFceTime.append(redTimes[0])
                    sTeam.Scores.activeLevTime.append(redTimes[1])
                    sTeam.Scores.activeBstTime.append(redTimes[2])
                    sTeam.Scores.cubesAtActiveFce.append(redCubesAtTime[0])
                    sTeam.Scores.cubesAtActiveLev.append(redCubesAtTime[1])
                    sTeam.Scores.cubesAtActiveBst.append(redCubesAtTime[2])

            #print("Blue Vault opperated by " + str(blueVaultTeam) + " for match " +str(count))
            for sTeam in Team.team_list:
                if sTeam.number == blueVaultTeam:
                    sTeam.Scores.activeFceTime.append(blueTimes[0])
                    sTeam.Scores.activeLevTime.append(blueTimes[1])
                    sTeam.Scores.activeBstTime.append(blueTimes[2])
                    sTeam.Scores.cubesAtActiveFce.append(blueCubesAtTime[0])
                    sTeam.Scores.cubesAtActiveLev.append(blueCubesAtTime[1])
                    sTeam.Scores.cubesAtActiveBst.append(blueCubesAtTime[2])

            count +=1

def get_power_up_times(rows, alliance):
    global vAlliance
    global vForceTime
    global vLevTime
    global vBoostTime

    powerUpTimes = []
    for row in rows:
        if row[vAlliance] == alliance:
            fceTime = time_min_to_sec(row[vForceTime]) if row[vForceTime] else 0
            levTime = time_min_to_sec(row[vLevTime]) if row[vLevTime] else 0
            bstTime = time_min_to_sec(row[vBoostTime]) if row[vBoostTime] else 0
            powerUpTimes.append(fceTime)
            powerUpTimes.append(levTime)
            powerUpTimes.append(bstTime)
            
    return powerUpTimes

def get_num_cubes_at_time(rows, alliance):
    global vAlliance
    global vFceCubesAtTime
    global vLevCubesAtTime
    global vBstCubesAtTime

    numCubesAtTime = []
    for row in rows:
        if row[vAlliance] == alliance:
            numCubesAtTime.append(row[vFceCubesAtTime])
            numCubesAtTime.append(row[vLevCubesAtTime])
            numCubesAtTime.append(row[vBstCubesAtTime])

    return numCubesAtTime

def get_num_cubes(rows, alliance):
    global vAlliance
    global vForceCubes
    global vLevCubes
    global vBoostCubes

    numCubes = 0
    for row in rows:
        if row[vAlliance] == alliance:
            numCubes += (row[vForceCubes] + row[vLevCubes] + row[vBoostCubes])

    return numCubes

            
def time_min_to_sec(time):
    if not time == "Start Match":
	mins = float(time[:time.find(':')])
	sec = float(time[time.find(':')+1:])
	sec += mins*60
	#print(sec)
	return sec
    else:
        return 0.0


