import argparse
import os

print("start...")

# Handle arguments
parser = argparse.ArgumentParser()
# All
parser.add_argument("-m", "--module", help="module")
# FTP
parser.add_argument("-d", "--date", help="cob date", type=int)
parser.add_argument("-f", "--feed", help="feed id", type=int)
# Parser
parser.add_argument("--host", help="host name")
parser.add_argument("--id", help="instance id", type=int)

args = parser.parse_args()
param_module = args.module
param_date = args.date
param_feed = args.feed
param_host = args.host
param_id = args.id

if param_module != None:
    print("param - module is: %s"%(param_module))
if param_date != None:
    print("param - date is: %s"%(param_date))
if param_feed != None:
    print("param - feed is: %s"%(param_feed))
if param_host != None:
    print("param - host is: %s"%(param_host))
if param_id != None:
    print("param - instance id is: %s"%(param_id))

# Call start cmd
if param_module == 'msg':
    os.system("start \"msg\" ; start.cmd msg")
elif param_module == 'ftp':
    if param_date != None and param_feed != None:
        ftp_args = "-d %s -f %s"%(param_date, param_feed)
    else:
        ftp_args = ""
    os.system("start \"ftp\" ; start.cmd ftp " + ftp_args)
elif param_module == 'receiver':
    os.system("start \"receiver\" ; start.cmd receiver")
elif param_module == 'dispatcher':
    os.system("start \"dispatcher\" ; start.cmd dispatcher")
elif param_module == 'parser':
    if param_host != None and param_id != None:
        parser_args = "--host %s --id %s"%(param_host, param_id)
    elif param_id != None:
        parser_args = "--id %s"%(param_id)
    else:
        parser_args = ""
    os.system("start \"parser\" ; start.cmd parser " + parser_args)
elif param_module == 'loader':
    os.system("start \"loader\" ; start.cmd loader")
elif param_module == 'zkserver':
    os.system("start \"zkserver\" ; start.cmd zkserver")
elif param_module == 'zkclient':
    os.system("start \"zkclient\" ; start.cmd zkclient")
elif param_module == 'geode_start':
    os.system("start \"geode_start\" ; start.cmd geode_start")
elif param_module == 'geode_stop':
    os.system("start \"geode_stop\" ; start.cmd geode_stop")
elif param_module == 'cache':
    os.system("start \"cache\" ; start.cmd cache")
elif param_module == 'all':
    os.system("start \"msg\" ; start.cmd msg")
    os.system("start \"zkserver\" ; start.cmd zkserver")
    os.system("start \"receiver\" ; start.cmd receiver")
    os.system("start \"dispatcher\" ; start.cmd dispatcher")
    os.system("start \"parser\" ; start.cmd parser")
    os.system("start \"loader\" ; start.cmd loader")
    os.system("start \"geode_start\" ; start.cmd geode_start")
    time.sleep(10)
    os.system("start \"cache\" ; start.cmd cache")
else:
    print("please select a correct module")

print("end...")