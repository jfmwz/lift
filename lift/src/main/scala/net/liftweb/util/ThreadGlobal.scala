package net.liftweb.util

/*                                                *\
 (c) 2006-2007 WorldWide Conferencing, LLC
 Distributed under an Apache License
 http://www.apache.org/licenses/LICENSE-2.0
 \*                                                 */

class ThreadGlobal[T]
{
  private val threadLocal = new ThreadLocal[T]
  
  def value: T = threadLocal.get
  
  def set(v: T): ThreadGlobal[T] = {
    threadLocal.set(v)
    this
  }
  
  def doWith[R](x: T)(f : => R) : R = {
    val original = value
    try {
      threadLocal.set(x)
      f
    } finally {
      threadLocal.set(original)
    }
  }
}
