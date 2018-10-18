#------------------------------------------------------------------------------
# Compiles all of the databases into one
#------------------------------------------------------------------------------
import sqlite3
import os

from team import *
from controller.windows import cteamdata
import model

conn = sqlite3.connect('C:\\Workspace_2018\\pyTK 2018\\scouting.db')
c = conn.cursor()

filePath = "C:\\Workspace_2018\\pyTK 2018\\Sqlite Database\\"


def create_table():
    global conn
    global c
    c.executescript('''
    CREATE TABLE IF NOT EXISTS CompetitionsAll( CompId TEXT, CompName TEXT,
              CompDate TEXT);
    CREATE TABLE IF NOT EXISTS CubesAll(PrimaryKey INTEGER, CompId TEXT, MatchNumber INTEGER,
              TeamNumber INTEGER, Balance TEXT, Time TEXT, Phase TEXT,
              PRIMARY KEY(MatchNumber, TeamNumber, Phase));
    CREATE TABLE IF NOT EXISTS MatchesAll( CompId TEXT, MatchNumber INTEGER,
              TeamNumber INTEGER, MatchPosition INTEGER,
              PRIMARY KEY(MatchNumber, TeamNumber, MatchPosition));
    CREATE TABLE IF NOT EXISTS OwnershipAll( ID INTEGER PRIMARY KEY, CompId TEXT,
              MatchNumber INTEGER, Balance TEXT, Owner TEXT, Time TEXT);
    CREATE TABLE IF NOT EXISTS PitDataAll( TeamNumber INTEGER PRIMARY KEY,
              AutoBaseline INTEGER, AutoCubeInSwitch INTEGER, AutoCubeInScale INTEGER,
              AutoCubeInExchange INTEGER, AutoOther INTEGER, CycleGround INTEGER,
              CyclePortal INTEGER, CycleSwitches INTEGER, Climb TEXT, GetSwitch INTEGER,
              GetScale INTEGER, FloorPickUp INTEGER, IntakeAndMech TEXT, DriveTrain TEXT,
              ProgLang TEXT, Comments TEXT, Speed TEXT);
    CREATE TABLE IF NOT EXISTS StatsAll( CompId TEXT, MatchNumber INTEGER,
              TeamNumber INTEGER, MatchPosition INTEGER, NoShow INTEGER, HadAuto INTEGER,
              CrossedAutoLine INTEGER, AutoPickedUpCube INTEGER, AutoCubesInSwitch1 INTEGER,
              AutoCubesInScale INTEGER, AutoCubesInSwitch2 INTEGER, AutoCubesInExchange INTEGER,
              TeleCubesInSwitch1 INTEGER, TeleCubesInSwitch2 INTEGER, TeleCubesInScale INTEGER,
              TeleCubesInExchange INTEGER, PickedUpCube INTEGER, Climb INTEGER,
              ClimbAssist INTEGER, Parking INTEGER, HumanPlayer INTEGER, Disabled INTEGER,
              RedCard INTEGER, YellowCard INTEGER, Foul INTEGER, TechFoul INTEGER,
              PRIMARY KEY(MatchNumber, TeamNumber , MatchPosition));
    CREATE TABLE IF NOT EXISTS TeamsAll( TeamNumber INTEGER not null PRIMARY KEY, TeamName TEXT);
    CREATE TABLE IF NOT EXISTS VaultAll(CompId TEXT, MatchNumber INTEGER, Alliance TEXT,
              ForceTime TEXT, ForceCubes INTEGER, LevTime TEXT, LevCubes INTEGER,
              BoostTime TEXT, BoostCubes INTEGER, ForceCubesAtTime INTEGER, LevitateCubesAtTime INTEGER,
              BoostCubesAtTime INTEGER,
              PRIMARY KEY(MatchNumber, Alliance));
    CREATE TABLE IF NOT EXISTS Alliances( AllianceNum INTEGER not null PRIMARY KEY, TeamNum INTEGER);
    CREATE TABLE IF NOT EXISTS TeamInfo(TeamNum INTEGER PRIMARY KEY, NumMatch INTEGER,
              PNoShow REAL, OffWS REAL,
              DefWS REAL, TotalWS REAL,
              NegWS REAL, AutoWS REAL,
              PHadAuto REAL, PCrossAutoLine REAL,
              PAutoPickedUpCube REAL, AvgAutoNumOwnChangesSw1 REAL,
              AvgAutoNumOwnChangesSw2 REAL, AvgAutoNumOwnChangesScl REAL,
              AvgAutoCubesInSw1 REAL, AvgAutoCubesInSw2 REAL,
              AvgAutoCubesInScl REAL, AvgAutoCubesInEx REAL,
              AvgAutoOwnGainSw1 REAL, AvgAutoOwnedSw1 REAL,
              AvgAutoOwnGainSw2 REAL, AvgAutoOwnedSw2 REAL,
              AvgAutoOwnGainScl REAL, AvgAutoOwnedScl REAL,
              TeleWS REAL, AvgTelePickedUpCube REAL,
              AvgTeleCubesInSw1 REAL, AvgTeleCubesInSw2 REAL,
              AvgTeleCubesInScl REAL, AvgTeleCubesInEx REAL,
              AvgTeleOwnGainSw1 REAL, AvgTeleOwnedSw1 REAL,
              AvgTeleOwnGainSw2 REAL, AvgTeleOwnedSw2 REAL,
              AvgTeleOwnGainScl REAL, AvgTeleOwnedScl REAL,
              AvgTeleNumOwnChangesSw1 REAL, AvgTeleNumOwnChangesSw2 REAL,
              AvgTeleNumOwnChangesScl REAL, PClimb REAL,
              PClimbAssist REAL, PParking REAL,
              PHadFoul REAL, PTechFoul REAL,
              PFoul REAL, PYellowCard REAL,
              PRedCard REAL, PDisabled REAL,
              PHumanPlayer REAL, AvgNumCubesInVault REAL,
              AvgActiveFceTime REAL, AvgActiveLevTime REAL,
              AvgActiveBstTime REAL, AvgCubesAtActiveFce REAL,
              AvgCubesAtActiveLev REAL, AvgCubesAtActiveBst REAL,
              MaxAutoNumOwnChangesSw1 INTEGER, MinAutoNumOwnChangesSw1 INTEGER,
              MaxAutoNumOwnChangesSw2 INTEGER, MinAutoNumOwnChangesSw2 INTEGER,
              MaxAutoNumOwnChangesScl INTEGER, MinAutoNumOwnChangesScl INTEGER,
              MaxAutoCubesInSw1 INTEGER, MinAutoCubesInSw1 INTEGER,
              MaxAutoCubesInSw2 INTEGER, MinAutoCubesInSw2 INTEGER,
              MaxAutoCubesInScl INTEGER, MinAutoCubesInScl INTEGER,
              MaxAutoCubesInEx INTEGER, MinAutoCubesInEx INTEGER,
              MaxAutoOwnGainSw1 INEGER, MinAutoOwnGainSw1 INTEGER,
              MaxAutoOwnedSw1 INTEGER, MinAutoOwnedSw1 INTEGER,
              MaxAutoOwnGainSw2 INEGER, MinAutoOwnGainSw2 INTEGER,
              MaxAutoOwnedSw2 INTEGER, MinAutoOwnedSw2 INTEGER,
              MaxAutoOwnGainScl INEGER, MinAutoOwnGainScl INTEGER,
              MaxAutoOwnedScl INTEGER, MinAutoOwnedScl INTEGER,
              MaxTeleNumOwnChangesSw1 INTEGER, MinTeleNumOwnChangesSw1 INTEGER,
              MaxTeleNumOwnChangesSw2 INTEGER, MinTeleNumOwnChangesSw2 INTEGER,
              MaxTeleNumOwnChangesScl INTEGER, MinTeleNumOwnChangesScl INTEGER,
              MaxTeleCubesInSw1 INTEGER, MinTeleCubesInSw1 INTEGER,
              MaxTeleCubesInSw2 INTEGER, MinTeleCubesInSw2 INTEGER,
              MaxTeleCubesInScl INTEGER, MinTeleCubesInScl INTEGER,
              MaxTeleCubesInEx INTEGER, MinTeleCubesInEx INTEGER,
              MaxTeleOwnGainSw1 INEGER, MinTeleOwnGainSw1 INTEGER,
              MaxTeleOwnedSw1 INTEGER, MinTeleOwnedSw1 INTEGER,
              MaxTeleOwnGainSw2 INEGER, MinTeleOwnGainSw2 INTEGER,
              MaxTeleOwnedSw2 INTEGER, MinTeleOwnedSw2 INTEGER,
              MaxTeleOwnGainScl INEGER, MinTeleOwnGainScl INTEGER,
              MaxTeleOwnedScl INTEGER, MinTeleOwnedScl INTEGER,
              MaxNumCubesInVault INTEGER, MinNumCubesInVault INTEGER,
              MaxActiveFceTime INTEGER, MinActiveFceTime INTEGER,
              MaxActiveLevTime INTEGER, MinActiveLevTime INTEGER,
              MaxActiveBstTime INTEGER, MinActiveBstTime INTEGER,
              MaxCubesAtActiveFce INTEGER, MinCubesAtActiveFce INTEGER,
              MaxCubesAtActiveLev INTEGER, MinCubesAtActiveLev INTEGER,
              MaxCubesAtActiveBst INTEGER, MinCubesAtActiveBst INTEGER)''')

def add_data():
    model.imported = False
    global conn
    global c
    global filePath
    dirList = os.listdir(filePath)
    for filename in dirList:
        tabletconn = sqlite3.connect(filePath + filename)
        print (filename)
        tc = tabletconn.cursor()
        try:
            tc.execute('SELECT * FROM Matches')
            matches = tc.fetchall()
            c.executemany('INSERT OR REPLACE INTO MatchesAll VALUES (?, ?, ?, ?)', matches)
            model.imported = True
            print("successfully imported matches")
        except Exception as e:
            print ("exception in matches",e)
            model.imported = False

        try:
            tc.execute('SELECT * FROM Teams')
            teams = tc.fetchall()
            c.executemany('INSERT OR REPLACE INTO TeamsAll VALUES (?, ?)', teams)
            model.imported = True
            print("successfully imported teams")
        except Exception as e:
            print ("exception in teams",e)
            model.imported = False

        try:
            tc.execute('SELECT * FROM Competitions')
            comps = tc.fetchall()
            c.executemany('INSERT OR REPLACE INTO CompetitionsAll VALUES (?, ?, ?)', comps)
            model.imported = True
            print("successfully imported comps")
        except Exception as e:
            print ("exception in comps",e)
            model.imported = False

        try:
            tc.execute('SELECT * FROM Ownership')
            ownerships = tc.fetchall()
            c.executemany('INSERT OR REPLACE INTO OwnershipAll VALUES (?, ?, ?, ?, ?, ?)', ownerships)
            model.imported = True
            print("successfully imported ownership")
        except Exception as e:
            print ("exception in ownership",e)

        try:
            tc.execute('SELECT * FROM PitData')
            pitData = tc.fetchall()
            c.executemany('INSERT OR REPLACE INTO PitDataAll VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)', pitData)
            model.imported = True
            print("successfully imported pitdata") 
        except Exception as e:
            print ("exception in pitdata",e)
            model.imported = False

        try:
            tc.execute('SELECT * FROM Stats')
            stats = tc.fetchall()
            c.executemany('INSERT OR REPLACE INTO StatsAll VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)', stats)
            model.imported = True
            print("successfully imported stats") 
        except Exception as e:
            print ("exception in stats",e)
            
        try:
            tc.execute('SELECT * FROM Cubes')
            cubes = tc.fetchall()
            c.executemany('INSERT OR REPLACE INTO CubesAll VALUES (?, ?, ?, ?, ?, ?, ?)', cubes)
            model.imported = True
            print("successfully imported cubes") 
        except Exception as e:
            print ("exception in cubes",e)

        try:
            tc.execute('SELECT * FROM Vault')
            vault = tc.fetchall()
            c.executemany('INSERT OR REPLACE INTO VaultAll VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)', vault)
            model.imported = True
            print("successfully imported vault") 
        except Exception as e:
            print ("exception in vault",e)
            
            
        tc.close()
def add_teamInfo(team):
    global conn
    global c
    teamInfo = []
    controller = cteamdata.TeamDataController()
    teamInfo.append(team.number)
    for x, y in controller.dataLabelVals:
        try:
            teamInfo.append(float(team.getAttr(x)))
        except:
            teamInfo.append(float((team.getAttr(x)[:team.getAttr(x).find("%")])))
    for x, y in controller.maxminLabelVals:
            teamInfo.append(int(team.Scores.getAttr(x)))
    temp = []
    temp.append(tuple(teamInfo))
    c.executemany('INSERT OR REPLACE INTO TeamInfo VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)', temp)
    conn.commit()
    
    
def add_matches():
    global conn
    global c
    global filePath
    dirList = os.listdir(filePath)
    for filename in dirList:
        tabletconn = sqlite3.connect(filePath + filename)
        tc = tabletconn.cursor()
        
        tc.execute('SELECT * FROM Matches')
        matches = tc.fetchall()
        c.executemany('INSERT OR REPLACE INTO MatchesAll VALUES (?, ?, ?, ?)', matches)
    
def add_teams():
    global conn
    global c
    global filePath
    dirList = os.listdir(filePath)
    for filename in dirList:
        tabletconn = sqlite3.connect(filePath + filename)
        tc = tabletconn.cursor()
        
        tc.execute('SELECT * FROM Teams')
        teams = tc.fetchall()
        c.executemany('INSERT OR REPLACE INTO TeamsAll VALUES (?, ?, ?)', teams)
    
def add_competitions():
    global conn
    global c
    global filePath
    dirList = os.listdir(filePath)
    for filename in dirList:
        tabletconn = sqlite3.connect(filePath + filename)
        tc = tabletconn.cursor()
        
        tc.execute('SELECT * FROM Competitions')
        comps = tc.fetchall()
        c.executemany('INSERT OR REPLACE INTO CompetitionsAll VALUES (?, ?, ?)', comps)

def add_ownerships():
    global conn
    global c
    global filePath
    dirList = os.listdir(filePath)
    for filename in dirList:
        tabletconn = sqlite3.connect(filePath + filename)
        tc = tabletconn.cursor()
        
        tc.execute('SELECT * FROM Ownerships')
        ownerships = tc.fetchall()
        c.executemany('INSERT OR REPLACE INTO OwnershipsAll VALUES (?, ?, ?, ?, ?, ?, ?, ?)', ownerships)

def add_pitData():
    global conn
    global c
    global filePath
    dirList = os.listdir(filePath)
    for filename in dirList:
        tabletconn = sqlite3.connect(filePath + filename)
        tc = tabletconn.cursor()
        
        tc.execute('SELECT * FROM PitData')
        pitData = tc.fetchall()
        c.executemany('INSERT OR REPLACE INTO PitDataAll VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)', pitData)
def add_stats():
    global conn
    global c
    global filePath
    dirList = os.listdir(filePath)
    for filename in dirList:
        tabletconn = sqlite3.connect(filePath + filename)
        tc = tabletconn.cursor()
        
        tc.execute('SELECT * FROM Stats')
        stats = tc.fetchall()
        c.executemany('INSERT OR REPLACE INTO StatsAll VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)', stats)
def add_cubes():
    global conn
    global c
    global filePath
    dirList = os.listdir("C:\Scouting2018\pyTK 2018\dataCollection")
    for filename in dirList:
        tabletconn = sqlite3.connect(filePath + filename)
        tc = tabletconn.cursor()

        tc.execute('SELECT * FROM Cubes')
        cubes = tc.fetchall()
        c.executemany('INSERT OR REPLACE INTO CubesAll VALUES (?, ?, ?, ?, ?, ?, ?)', cubes)
def add_vault():
    global conn
    global c
    global filePath
    dirList = os.listdir("C:\Scouting2018\pyTK 2018\dataCollection")
    for filename in dirList:
        tabletconn = sqlite3.connect(filePath + filename)
        tc = tabletconn.cursor()

        tc.execute('SELECT * FROM Vault')
        vault = tc.fetchall()
        c.executemany('INSERT OR REPLACE INTO VaultAll VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)', vault)

def clear_importData():
    model.imported = False
    model.pitImported = False
    Team.available = []
    Team.team_list = []


