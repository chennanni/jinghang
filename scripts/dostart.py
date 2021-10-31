import argparse
import os

print("start...")

parser = argparse.ArgumentParser()
parser.add_argument("-m", "--module", help="module", default="")
parser.add_argument("-d", "--date", help="cob date", type=int, default=0)
parser.add_argument("-f", "--feed", help="feed id", type=int, default=0)

args = parser.parse_args()
param_module = args.module
param_date = args.date
param_feed = args.feed
print("param - module is: %s"%(param_module))
print("param - date is: %s"%(param_date))
print("param - feed is: %s"%(param_feed))

if param_module == 'msg':
    os.system("start \"msg\" ; start.cmd msg")
elif param_module == 'ftp':
    if param_date != 0 and param_feed != 0:
        ftp_args = "-d %s -f %s"%(param_date, param_feed)
    else:
        ftp_args = ""
    os.system("start \"ftp\" ; start.cmd ftp " + ftp_args)
elif param_module == 'receiver':
    os.system("start \"receiver\" ; start.cmd receiver")
elif param_module == 'parser':
    os.system("start \"parser\" ; start.cmd parser")
elif param_module == 'loader':
    os.system("start \"loader\" ; start.cmd loader")
elif param_module == 'all':
    os.system("start \"msg\" ; start.cmd msg")
    os.system("start \"receiver\" ; start.cmd receiver")
    os.system("start \"parser\" ; start.cmd parser")
    os.system("start \"loader\" ; start.cmd loader")

print("end...")